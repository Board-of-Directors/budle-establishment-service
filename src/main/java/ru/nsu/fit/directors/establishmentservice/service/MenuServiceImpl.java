package ru.nsu.fit.directors.establishmentservice.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.directors.establishmentservice.dto.request.ChangeCategoryRequest;
import ru.nsu.fit.directors.establishmentservice.dto.request.ChangeProductRequest;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestProductDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseMenuCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ShortResponseMenuCategoryDto;
import ru.nsu.fit.directors.establishmentservice.enums.EntityType;
import ru.nsu.fit.directors.establishmentservice.exception.EntityNotFoundException;
import ru.nsu.fit.directors.establishmentservice.exception.EstablishmentNotFoundException;
import ru.nsu.fit.directors.establishmentservice.mapper.MenuMapper;
import ru.nsu.fit.directors.establishmentservice.model.Category;
import ru.nsu.fit.directors.establishmentservice.model.MenuCategory;
import ru.nsu.fit.directors.establishmentservice.model.Product;
import ru.nsu.fit.directors.establishmentservice.model.Restaurant;
import ru.nsu.fit.directors.establishmentservice.repository.EstablishmentRepository;
import ru.nsu.fit.directors.establishmentservice.repository.MenuRepository;
import ru.nsu.fit.directors.establishmentservice.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final EstablishmentRepository establishmentRepository;
    private final EstablishmentService establishmentService;
    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    private final ProductRepository productRepository;

    @Nonnull
    @Override
    public List<ResponseMenuCategoryDto> getMenu(long establishmentId) {
        Restaurant restaurant =
            (Restaurant) establishmentRepository.findByCategoryAndId(Category.restaurant, establishmentId)
                .orElseThrow(() -> new EstablishmentNotFoundException(establishmentId));

        return menuMapper.toDto(menuRepository.findAllByEstablishment(restaurant));
    }

    @Override
    public void createCategory(RequestCategoryDto category) {
        menuRepository.save(
            menuMapper.toModel(
                category,
                establishmentService.getEstablishmentById(category.getEstablishmentId())
            )
        );
    }

    @Override
    public void createProduct(RequestProductDto product) {
        productRepository.save(menuMapper.toModel(
            product,
            menuRepository.findById(product.getCategoryId()).orElseThrow()
        ));
    }

    @Override
    public void deleteCategory(long categoryId) {
        menuRepository.deleteById(categoryId);
    }

    @Override
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }

    @Nonnull
    @Override
    public List<ShortResponseMenuCategoryDto> getShortMenu(long establishmentId) {
        Restaurant restaurant =
            (Restaurant) establishmentRepository.findByCategoryAndId(Category.restaurant, establishmentId)
                .orElseThrow(() -> new EstablishmentNotFoundException(establishmentId));
        return menuMapper.toShortDto(
            menuRepository.findAllByEstablishment(restaurant),
            establishmentId
        );
    }

    @Override
    public void changeProduct(ChangeProductRequest changeProductRequest) {
        Product product = productRepository.findById(changeProductRequest.productId())
            .orElseThrow(() -> new EntityNotFoundException(EntityType.PRODUCT, changeProductRequest.productId()));
        menuMapper.updateModel(product, changeProductRequest);
        productRepository.save(product);
    }

    @Override
    public void changeCategory(ChangeCategoryRequest changeCategoryRequest) {
        MenuCategory menuCategory = menuRepository.findById(changeCategoryRequest.id())
            .orElseThrow(() -> new EntityNotFoundException(EntityType.CATEGORY, changeCategoryRequest.id()));
        Optional.ofNullable(changeCategoryRequest.name()).ifPresent(menuCategory::setName);
        menuRepository.save(menuCategory);
    }

}
