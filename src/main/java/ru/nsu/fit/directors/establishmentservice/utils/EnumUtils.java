package ru.nsu.fit.directors.establishmentservice.utils;

import java.util.Arrays;

import lombok.experimental.UtilityClass;
import ru.nsu.fit.directors.establishmentservice.exception.EnumNotFoundException;

@UtilityClass
public class EnumUtils {
    public <E extends Enum<E>> E parseEnum(String name, Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
            .filter(value -> value.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new EnumNotFoundException(enumClass, name));
    }
}