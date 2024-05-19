package ru.nsu.fit.directors.establishmentservice.model;

import com.amazonaws.util.StringUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseSubcategoryDto;
import ru.nsu.fit.directors.establishmentservice.enums.CuisineCountry;
import ru.nsu.fit.directors.establishmentservice.enums.HotelStars;
import ru.nsu.fit.directors.establishmentservice.enums.ParseableEnum;
import ru.nsu.fit.directors.establishmentservice.exception.IncorrectCategoryException;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

@Getter
@RequiredArgsConstructor
public enum Category implements ParseableEnum {
    restaurant("Рестораны", new ResponseSubcategoryDto(CuisineCountry.getVariants(), "Тип кухни", "cuisineCountry")),
    hotel("Отели", new ResponseSubcategoryDto(HotelStars.getVariants(), "Количество звезд", "starsCount")),
    game_club("Игровые клубы", new ResponseSubcategoryDto()),
    barbershop("Парикмахерские", new ResponseSubcategoryDto()),
    ;

    private final String readableName;
    private final ResponseSubcategoryDto variants;

    @Nonnull
    static public Category getEnumByValue(String value) {
        for (Category e : Category.values()) {
            if (e.readableName.equals(value)) {
                return e;
            }
        }
        throw new IncorrectCategoryException();
    }

    @Nonnull
    static public String getCategories() {
        return Arrays.stream(Category.values())
            .map(x -> x.readableName)
            .collect(Collectors.joining(StringUtils.COMMA_SEPARATOR));
    }

    @Nonnull
    @Override
    public String getParseableName() {
        return readableName;
    }
}

