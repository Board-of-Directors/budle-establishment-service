package ru.nsu.fit.directors.establishmentservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import org.springframework.lang.Nullable;


@Builder
public record RequestGetEstablishmentParameters(
    String name,
    @Nullable String category,
    @Nullable Boolean hasMap,
    @Nullable Boolean hasCardPayment,
    @Min(value = 5, message = "Не может быть меньше 5 рабочих дней.")
    @Max(value = 7, message = "Не может быть больше 7 рабочих дней.")
    Integer workingDayCount
) {

    public RequestGetEstablishmentParameters {
        name = name == null ? "" : name;
        workingDayCount = workingDayCount == null ? 7 : workingDayCount;
    }
}
