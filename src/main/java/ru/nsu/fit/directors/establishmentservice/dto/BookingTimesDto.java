package ru.nsu.fit.directors.establishmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingTimesDto {
    private String startTime;
    private String endTime;
}
