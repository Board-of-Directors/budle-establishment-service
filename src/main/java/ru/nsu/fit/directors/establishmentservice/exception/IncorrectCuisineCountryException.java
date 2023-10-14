package ru.nsu.fit.directors.establishmentservice.exception;

import ru.nsu.fit.directors.establishmentservice.enums.CuisineCountry;

public class IncorrectCuisineCountryException extends BaseException {
    static private final String EXCEPTION_TYPE = "IncorrectCuisineCountryException";
    static private final String EXCEPTION_MESSAGE =
        "Не существует кухни с таким названием. Попробуйте один из вариантов: " + CuisineCountry.getVariants()
            .stream()
            .reduce(" ", (acc, src) -> acc + src);

    public IncorrectCuisineCountryException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TYPE);
    }
}
