package ru.nsu.fit.directors.establishmentservice.exception;

import ru.nsu.fit.directors.establishmentservice.model.Category;

public class IncorrectCategoryException extends BaseException {
    static private final String EXCEPTION_TYPE = "IncorrectCategoryException";
    static private final String EXCEPTION_MESSAGE =
        "Неверно указана категория. Попробуйте ввести категорию из предложенных" + Category.getCategories();

    public IncorrectCategoryException() {
        super(EXCEPTION_MESSAGE, EXCEPTION_TYPE);
    }
}
