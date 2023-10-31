package ru.nsu.fit.directors.establishmentservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseMenuCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ShortResponseMenuCategoryDto;
import ru.nsu.fit.directors.establishmentservice.service.MenuService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "establishment/menu")
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
}
