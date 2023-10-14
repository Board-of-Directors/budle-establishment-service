package ru.nsu.fit.directors.establishmentservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RequestRestaurantDto extends RequestEstablishmentDto {
    @NotNull(message = "Информация о кухне ресторана не может быть не задана.")
    private String cuisineCountry;
}
