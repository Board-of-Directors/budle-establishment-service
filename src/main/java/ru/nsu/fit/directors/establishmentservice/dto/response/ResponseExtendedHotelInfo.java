package ru.nsu.fit.directors.establishmentservice.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseExtendedHotelInfo extends ResponseExtendedEstablishmentInfo {
    private int starsCount;
}
