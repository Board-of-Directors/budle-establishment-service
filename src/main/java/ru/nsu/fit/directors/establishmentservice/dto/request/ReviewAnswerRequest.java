package ru.nsu.fit.directors.establishmentservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewAnswerRequest(
    @NotNull(message = "Идентификатор отзыва не может отсутствовать.")
    Long reviewId,
    @NotBlank(message = "Текст ответа не может быть пустым.")
    String answer
) {
}
