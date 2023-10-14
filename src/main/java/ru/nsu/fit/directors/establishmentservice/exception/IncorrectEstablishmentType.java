package ru.nsu.fit.directors.establishmentservice.exception;

public class IncorrectEstablishmentType extends BaseException {
    public IncorrectEstablishmentType() {
        super("Данного типа заведения не существует", "IncorrectEstablishmentType");
    }
}
