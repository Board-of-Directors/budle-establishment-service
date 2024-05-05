package ru.nsu.fit.directors.establishmentservice.exception;

public class EnumNotFoundException extends BaseException {
    public <E> EnumNotFoundException(Class<E> type, String name) {
        super(
            "Не найдена константа '%s' с названием '%s'".formatted(type.getSimpleName(), name),
            "EnumNotFoundException"
        );
    }
}
