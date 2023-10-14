package ru.nsu.fit.directors.establishmentservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RequestHotelDto extends RequestEstablishmentDto {
    @Min(value = 1, message = "Количество звезд не может быть меньше 1.")
    @Max(value = 5, message = "Количество звезд не может быть больше 5.")
    private int starsCount;
}
