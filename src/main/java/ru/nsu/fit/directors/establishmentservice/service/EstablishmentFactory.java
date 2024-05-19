package ru.nsu.fit.directors.establishmentservice.service;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicBarbershopInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicGameClubInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicHotelInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicRestaurantInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedBarbershopInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedGameClubInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedHotelInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedRestaurantInfo;
import ru.nsu.fit.directors.establishmentservice.model.Barbershop;
import ru.nsu.fit.directors.establishmentservice.model.Category;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.GameClub;
import ru.nsu.fit.directors.establishmentservice.model.Hotel;
import ru.nsu.fit.directors.establishmentservice.model.Restaurant;

import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

@Component
@ParametersAreNonnullByDefault
public class EstablishmentFactory {
    private static final Map<Category, Class<? extends Establishment>> ENTITY_FACTORY = Map.ofEntries(
        Map.entry(Category.restaurant, Restaurant.class),
        Map.entry(Category.hotel, Hotel.class),
        Map.entry(Category.game_club, GameClub.class),
        Map.entry(Category.barbershop, Barbershop.class)
    );
    private static final Map<Category, Class<? extends ResponseBasicEstablishmentInfo>> BASIC_INFO_FACTORY =
        Map.ofEntries(
            Map.entry(Category.restaurant, ResponseBasicRestaurantInfo.class),
            Map.entry(Category.hotel, ResponseBasicHotelInfo.class),
            Map.entry(Category.game_club, ResponseBasicGameClubInfo.class),
            Map.entry(Category.barbershop, ResponseBasicBarbershopInfo.class)
        );
    private static final Map<Category, Class<? extends ResponseExtendedEstablishmentInfo>> EXTENDED_INFO_FACTORY =
        Map.ofEntries(
            Map.entry(Category.restaurant, ResponseExtendedRestaurantInfo.class),
            Map.entry(Category.hotel, ResponseExtendedHotelInfo.class),
            Map.entry(Category.game_club, ResponseExtendedGameClubInfo.class),
            Map.entry(Category.barbershop, ResponseExtendedBarbershopInfo.class)
        );

    @Nonnull
    public Class<? extends Establishment> getEstablishmentEntity(Category category) {
        return ENTITY_FACTORY.get(category);
    }

    @Nonnull
    public Class<? extends ResponseBasicEstablishmentInfo> getBasicInfo(Category category) {
        return BASIC_INFO_FACTORY.get(category);
    }

    @Nonnull
    public Class<? extends ResponseExtendedEstablishmentInfo> getExtendedInfo(Category category) {
        return EXTENDED_INFO_FACTORY.get(category);
    }
}
