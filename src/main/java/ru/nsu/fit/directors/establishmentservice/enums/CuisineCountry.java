package ru.nsu.fit.directors.establishmentservice.enums;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum CuisineCountry implements ParseableEnum {
    european("Европейская"),
    georgian("Грузинская"),
    asian("Азиатская"),
    russian("Русская"),
    vietnamese("Вьетнамская"),
    chinese("Китайская"),
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
