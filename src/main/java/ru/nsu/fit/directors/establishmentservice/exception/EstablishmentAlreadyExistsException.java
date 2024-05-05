package ru.nsu.fit.directors.establishmentservice.exception;

public class EstablishmentAlreadyExistsException extends BaseException {
    static final private String ERROR_TYPE = "EstablishmentAlreadyExists";

    public EstablishmentAlreadyExistsException(String name, String address) {
        super("Заведение с названием %s и адресом %s уже существует".formatted(name, address), ERROR_TYPE);
    }
}
