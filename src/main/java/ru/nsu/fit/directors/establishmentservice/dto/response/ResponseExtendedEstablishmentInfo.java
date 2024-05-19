package ru.nsu.fit.directors.establishmentservice.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;

import java.util.List;
import java.util.Set;

@JsonSubTypes({
    @JsonSubTypes.Type(value = ResponseExtendedRestaurantInfo.class, name = "restaurant"),
    @JsonSubTypes.Type(value = ResponseExtendedHotelInfo.class, name = "hotel"),
    @JsonSubTypes.Type(value = ResponseExtendedBarbershopInfo.class, name = "barbershop"),
    @JsonSubTypes.Type(value = ResponseExtendedGameClubInfo.class, name = "game_club")
})
@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseExtendedEstablishmentInfo extends ResponseBasicEstablishmentInfo {
    private List<ResponseTagDto> tags;
    private String description;
    private List<PhotoDto> photos;
    private String map;
    private List<ResponseWorkingHoursDto> workingHours;
    private String address;
}
