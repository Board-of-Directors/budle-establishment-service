package ru.nsu.fit.directors.establishmentservice.exception;

public class ErrorWhileParsingEstablishmentMapException extends BaseException {
    public ErrorWhileParsingEstablishmentMapException() {
        super("В ходе считывания карты произошла ошибка", "ErrorWhileParsingEstablishmentMapException");
    }
}
