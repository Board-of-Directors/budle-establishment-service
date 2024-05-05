package ru.nsu.fit.directors.establishmentservice.exception;

public class EstablishmentNotFoundException extends BaseException {
    static final private String ERROR_TYPE = "EstablishmentNotFound";

    public EstablishmentNotFoundException(Long id) {
        super("Заведения с id " + id + " не существует", ERROR_TYPE);
    }
}
