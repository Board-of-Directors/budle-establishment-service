package ru.nsu.fit.directors.establishmentservice.utils;

import java.util.Map;

import lombok.experimental.UtilityClass;
import ru.nsu.fit.directors.establishmentservice.model.Barbershop;
import ru.nsu.fit.directors.establishmentservice.model.Category;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.GameClub;
import ru.nsu.fit.directors.establishmentservice.model.Hotel;
import ru.nsu.fit.directors.establishmentservice.model.Restaurant;

@UtilityClass
public class EstablishmentFactoryUtils {
    private static final Map<Category, Establishment> ESTABLISHMENT_BY_CATEGORY = Map.ofEntries(
        Map.entry(Category.restaurant, new Restaurant()),
        Map.entry(Category.hotel, new Hotel()),
        Map.entry(Category.barbershop, new Barbershop()),
        Map.entry(Category.game_club, new GameClub())
    );
}
