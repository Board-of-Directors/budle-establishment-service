package ru.nsu.fit.directors.establishmentservice.dto.request;

import jakarta.validation.constraints.NotNull;

public record RequestReviewDto(
    Long userId,
    @NotNull(message = "Значение заведения не может быть не указано.")
    Long establishmentId,
    @NotNull(message = "Текст не может быть не задан.")
    String text,
    @NotNull(message = "Оценка не может быть не задана.")
    Integer score

) {
}
