package ru.nsu.fit.directors.establishmentservice.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.directors.establishmentservice.dto.request.ChangeCategoryRequest;
import ru.nsu.fit.directors.establishmentservice.dto.request.ChangeProductRequest;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestProductDto;
import ru.nsu.fit.directors.establishmentservice.service.MenuService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/internal/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class InternalMenuController {
    private final MenuService menuService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody RequestCategoryDto category) {
        menuService.createCategory(category);
    }

    @PutMapping
    public void changeCategory(@RequestBody ChangeCategoryRequest changeCategoryRequest){
        menuService.changeCategory(changeCategoryRequest);
    }

    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody RequestProductDto product) {
        menuService.createProduct(product);
    }

    @PutMapping(value = "/product")
    public void changeProduct(@RequestBody ChangeProductRequest changeProductRequest){
        menuService.changeProduct(changeProductRequest);
    }

    @DeleteMapping
    public void deleteCategory(@RequestParam long categoryId) {
        menuService.deleteCategory(categoryId);
    }

    @DeleteMapping(value = "/product")
    public void deleteProduct(@RequestParam long productId) {
        menuService.deleteProduct(productId);
    }
}
