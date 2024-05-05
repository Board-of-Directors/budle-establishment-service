package ru.nsu.fit.directors.establishmentservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum CuisineCountry {
    european("Европейская"),
    georgian("Грузинская"),
    asian("Азиатская"),
    russian("Русская"),
    vietnamese("Вьетнамская"),
    ;

    private final String value;

    public static List<String> getVariants() {
        return Arrays.stream(CuisineCountry.values())
            .map(CuisineCountry::getValue)
            .toList();
    }
}
