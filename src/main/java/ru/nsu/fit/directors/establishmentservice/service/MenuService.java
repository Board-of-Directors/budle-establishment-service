package ru.nsu.fit.directors.establishmentservice.service;

import jakarta.annotation.Nonnull;
import ru.nsu.fit.directors.establishmentservice.dto.request.ChangeCategoryRequest;
import ru.nsu.fit.directors.establishmentservice.dto.request.ChangeProductRequest;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestProductDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseMenuCategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ShortResponseMenuCategoryDto;

import java.util.List;

public interface MenuService {
    /**
     * Получение меню заведения.
     *
     * @param establishmentId идентификатор заведения для получения меню
     * @return список категорий меню
     */
    @Nonnull
    List<ResponseMenuCategoryDto> getMenu(long establishmentId);

    /**
     * Создание категории в меню.
     *
     * @param category создаваемая категория
     */
    void createCategory(RequestCategoryDto category);

    /**
     * Создание продукта в меню.
     *
     * @param product создаваемый продукт
     */
    void createProduct(RequestProductDto product);

    /**
     * Удаление соответсвующей категории.
     *
     * @param categoryId идентификатор категории
     */
    void deleteCategory(long categoryId);

    /**
     * Удаление соответствующего продукта.
     *
     * @param productId идентификатор продукта
     */
    void deleteProduct(long productId);

    /**
     * Получить краткий список категорий из меню.
     *
     * @param establishmentId идентификатор заведения
     * @return краткий список категорий
     */
    @Nonnull
    List<ShortResponseMenuCategoryDto> getShortMenu(long establishmentId);

    /**
     * Изменить продукт.
     *
     * @param changeProductRequest запрос редактирования продукта
     */
    void changeProduct(ChangeProductRequest changeProductRequest);

    /**
     * Изменить категорию.
     *
     * @param changeCategoryRequest запрос изменения категории
     */
    void changeCategory(ChangeCategoryRequest changeCategoryRequest);
}
