package ru.nsu.fit.directors.establishmentservice.enums;

import java.util.List;
import java.util.stream.Stream;

import jakarta.annotation.Nonnull;

public enum HotelStars {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    ;

    @Nonnull
    public static List<String> getVariants() {
        return Stream
            .iterate(1, i -> i + 1)
            .limit(5)
            .map(Object::toString)
            .toList();
    }
}
