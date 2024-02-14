package ru.nsu.fit.directors.establishmentservice.dto.request;

import jakarta.validation.constraints.NotNull;

public record RequestReviewDto(
    @NotNull(message = "Имя пользователя не может быть не задано.")
    String username,
    @NotNull(message = "Значение заведения не может быть не указано.")
    Long establishmentId,
    @NotNull(message = "Текст не может быть не задан.")
    String text,
    @NotNull(message = "Оценка не может быть не задана.")
    Integer score
) {
}
