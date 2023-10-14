package ru.nsu.fit.directors.establishmentservice.dto.response;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ResponseWorkingHoursDto {
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
