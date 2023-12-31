package ru.nsu.fit.directors.establishmentservice.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestProductDto;
import ru.nsu.fit.directors.establishmentservice.service.MenuService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/internal/menu")
public class InternalMenuController {
    private final MenuService menuService;

    @PostMapping
    public void add(@RequestBody RequestCategoryDto category) {
        menuService.createCategory(category);
    }

    @PostMapping(value = "/product")
    public void addProduct(@RequestBody RequestProductDto product) {
        menuService.createProduct(product);
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
