package ru.nsu.fit.directors.establishmentservice.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ResponseBasicRestaurantInfo.class, name = "restaurant"),
    @JsonSubTypes.Type(value = ResponseBasicHotelInfo.class, name = "hotel"),
    @JsonSubTypes.Type(value = ResponseBasicBarbershopInfo.class, name = "barbershop"),
    @JsonSubTypes.Type(value = ResponseBasicGameClubInfo.class, name = "game_club")
})
public class ResponseBasicEstablishmentInfo extends ResponseShortEstablishmentInfo {
    private Float rating;
    private String address;
    private String image;
    private Boolean hasMap;
    private Boolean hasCardPayment;

}
