package ru.nsu.fit.directors.establishmentservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityType {
    ESTABLISHMENT("Заведение"),
    REVIEW("Отзыв"),
    PRODUCT("Продукт"),
    CATEGORY("Категория"),
    ;

    private final String readableName;
}
