package ru.nsu.fit.directors.establishmentservice.utils;

import java.util.Arrays;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lombok.experimental.UtilityClass;
import ru.nsu.fit.directors.establishmentservice.enums.ParseableEnum;
import ru.nsu.fit.directors.establishmentservice.exception.EnumNotFoundException;

@UtilityClass
public class EnumUtils {

    @Nonnull
    public <E extends ParseableEnum> E parseEnum(String name, Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
            .filter(parseableEnum -> parseableEnum.getParseableName().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new EnumNotFoundException(enumClass, name));
    }

    @Nullable
    public <E extends ParseableEnum> E parseEnumByNullable(@Nullable String maybeName, Class<E> enumClass) {
        return Optional.ofNullable(maybeName)
            .map(name -> parseEnum(name, enumClass))
            .orElse(null);
    }

    @Nullable
    public <E extends Enum<E>> E findEnumByNullable(@Nullable String maybeName, Class<E> enumClass) {
        return Optional.ofNullable(maybeName)
            .map(name -> findEnum(name, enumClass))
            .orElse(null);
    }

    @Nonnull
    public <E extends Enum<E>> E findEnum(String name, Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
            .filter(parseableEnum -> parseableEnum.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new EnumNotFoundException(enumClass, name));
    }
}
