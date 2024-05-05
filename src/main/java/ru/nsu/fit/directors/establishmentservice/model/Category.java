package ru.nsu.fit.directors.establishmentservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseSubcategoryDto;
import ru.nsu.fit.directors.establishmentservice.enums.CuisineCountry;
import ru.nsu.fit.directors.establishmentservice.enums.HotelStars;
import ru.nsu.fit.directors.establishmentservice.exception.IncorrectCategoryException;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Category {
    restaurant("Рестораны", new ResponseSubcategoryDto(CuisineCountry.getVariants(), "Тип кухни", "cuisineCountry")),
    hotel("Отели", new ResponseSubcategoryDto(HotelStars.getVariants(), "Количество звезд", "starsCount")),
    game_club("Игровые клубы", new ResponseSubcategoryDto()),
    barbershop("Парикмахерские", new ResponseSubcategoryDto());

    private final String value;
    private final ResponseSubcategoryDto variants;

    static public Category getEnumByValue(String value) {
        for (Category e : Category.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        throw new IncorrectCategoryException();
    }

    static public String getCategories() {
        return Arrays.stream(Category.values()).map(x -> x.value).reduce(":", (str, acc) -> acc + "," + str);
    }
}

