package ru.nsu.fit.directors.establishmentservice.enums;


import lombok.Getter;
import ru.nsu.fit.directors.establishmentservice.exception.IncorrectCuisineCountryException;

import java.util.Arrays;
import java.util.List;

@Getter
public enum CuisineCountry {
    european("Европейская"),
    georgian("Грузинская"),
    asian("Азиатская"),
    russian("Русская"),
    vietnamese("Вьетнамская");


    private final String value;

    CuisineCountry(String value) {
        this.value = value;
    }

    static public CuisineCountry getEnumByValue(String value) {
        for (CuisineCountry e : CuisineCountry.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IncorrectCuisineCountryException();
    }

    public static List<String> getVariants() {
        return Arrays.stream(CuisineCountry.values())
            .map(CuisineCountry::getValue)
            .toList();
    }
}
