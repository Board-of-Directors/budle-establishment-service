package ru.nsu.fit.directors.establishmentservice.exception;

import ru.nsu.fit.directors.establishmentservice.enums.EntityType;

public class EntityNotFoundException extends BaseException {
    public EntityNotFoundException(EntityType entityType, Long id) {
        super("Не найден %s с идентификатором %s".formatted(entityType.getReadableName(), id), "EntityNotFound");
    }
}
