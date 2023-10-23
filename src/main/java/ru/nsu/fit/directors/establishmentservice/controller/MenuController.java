package ru.nsu.fit.directors.establishmentservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestProductDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseMenuCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ShortResponseMenuCategoryDto;
import ru.nsu.fit.directors.establishmentservice.service.MenuService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "establishment/menu")
@CrossOrigin(allowCredentials = "true", originPatterns = {"*"})
public class MenuController {
    private final MenuService menuService;

    @GetMapping(value = "/short")
    public List<ShortResponseMenuCategoryDto> getShort(@RequestParam long establishmentId) {
        return menuService.getShortMenu(establishmentId);
    }

    @GetMapping
    public List<ResponseMenuCategoryDto> get(@RequestParam long establishmentId) {
        return menuService.getMenu(establishmentId);
    }

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
