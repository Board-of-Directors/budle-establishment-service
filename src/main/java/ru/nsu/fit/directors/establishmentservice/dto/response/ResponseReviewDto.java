package ru.nsu.fit.directors.establishmentservice.dto.response;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record ResponseReviewDto(
    Long id,
    String username,
    String text,
    Integer score,
    LocalDate date,
    String answer
) {
}
