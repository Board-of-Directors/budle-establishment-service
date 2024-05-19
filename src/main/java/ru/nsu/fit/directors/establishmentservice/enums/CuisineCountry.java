package ru.nsu.fit.directors.establishmentservice.enums;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum CuisineCountry implements ParseableEnum {
    EUROPEAN("Европейская"),
    GEORGIAN("Грузинская"),
    ASIAN("Азиатская"),
    RUSSIAN("Русская"),
    VIETNAMESE("Вьетнамская"),
    CHINESE("Китайская"),
    ITALIAN("Итальянская"),
    INDIAN("Индийская"),
    MEXICAN("Мексиканская"),
    FRENCH("Французская"),
    TURKISH("Турецкая"),
    ;

    private final String readableName;

    @Nonnull
    public static List<String> getVariants() {
        return Arrays.stream(CuisineCountry.values())
            .map(CuisineCountry::getReadableName)
            .toList();
    }

    @Nonnull
    @Override
    public String getParseableName() {
        return readableName;
    }
}
