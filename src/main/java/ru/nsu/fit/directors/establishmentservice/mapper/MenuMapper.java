package ru.nsu.fit.directors.establishmentservice.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.request.ChangeProductRequest;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestProductDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseMenuCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseProductDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ShortResponseMenuCategoryDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.MenuCategory;
import ru.nsu.fit.directors.establishmentservice.model.Product;

import java.util.List;
import java.util.Optional;

@Component
public class MenuMapper {
    @Nonnull
    public List<ResponseMenuCategoryDto> toDto(List<MenuCategory> categories) {
        return categories.stream()
            .filter(category -> category.getParentCategoryId() == null)
            .map(this::toDto)
            .toList();

    }

    @Nonnull
    public ResponseMenuCategoryDto toDto(MenuCategory category) {
        return new ResponseMenuCategoryDto()
            .setId(category.getId())
            .setName(category.getName())
            .setProducts(category.getProducts().stream().map(this::toDto).toList())
            .setChildCategories(category.getChildCategories().stream().map(this::toDto).toList());
    }

    @Nonnull
    public ResponseProductDto toDto(Product product) {
        return new ResponseProductDto()
            .setId(product.getId())
            .setPrice(product.getPrice())
            .setName(product.getName())
            .setDescription(product.getDescription())
            .setWeightG(product.getWeightG())
            .setOnSale(product.isOnSale());
    }

    @Nonnull
    public MenuCategory toModel(RequestCategoryDto category, Establishment establishment) {
        return new MenuCategory()
            .setName(category.getName())
            .setParentCategoryId(category.getParentCategoryId())
            .setEstablishment(establishment);
    }

    @Nonnull
    public Product toModel(RequestProductDto product, MenuCategory menuCategory) {
        return new Product()
            .setName(product.getName())
            .setDescription(product.getDescription())
            .setWeightG(product.getWeightG())
            .setOnSale(product.getIsOnSale())
            .setPrice(product.getPrice())
            .setCategory(menuCategory);
    }

    @Nonnull
    public List<ShortResponseMenuCategoryDto> toShortDto(List<MenuCategory> categories, long establishmentId) {
        return categories.stream()
            .map(this::toShortDto)
            .map(dto -> dto.setEstablishmentId(establishmentId))
            .toList();
    }

    @Nonnull
    public ShortResponseMenuCategoryDto toShortDto(MenuCategory category) {
        return new ShortResponseMenuCategoryDto()
            .setId(category.getId())
            .setName(category.getName());
    }

    public void updateModel(Product product, ChangeProductRequest changeProductRequest) {
        Optional.ofNullable(changeProductRequest.name()).ifPresent(product::setName);
        Optional.ofNullable(changeProductRequest.description()).ifPresent(product::setDescription);
        Optional.ofNullable(changeProductRequest.isOnSale()).ifPresent(product::setOnSale);
        Optional.ofNullable(changeProductRequest.weightG()).ifPresent(product::setWeightG);
        Optional.ofNullable(changeProductRequest.price()).ifPresent(product::setPrice);

    }
}
