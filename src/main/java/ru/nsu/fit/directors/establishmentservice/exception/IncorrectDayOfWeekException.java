package ru.nsu.fit.directors.establishmentservice.exception;

public class IncorrectDayOfWeekException extends BaseException {
    static private final String EXCEPTION_TYPE = "IncorrectDayOfWeekException";
    static private final String EXCEPTION_MESSAGE = "Некорректно набран день недели. Повторите операцию.";

    public IncorrectDayOfWeekException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TYPE);
    }
}
