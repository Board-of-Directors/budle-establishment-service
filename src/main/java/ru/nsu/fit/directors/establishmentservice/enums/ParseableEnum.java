package ru.nsu.fit.directors.establishmentservice.enums;

import jakarta.annotation.Nonnull;

public interface ParseableEnum {
    /**
     * Получить имя, пригодное для парсинга.
     *
     * @return имя, пригодное для парсинга
     */
    @Nonnull
    String getParseableName();
}
