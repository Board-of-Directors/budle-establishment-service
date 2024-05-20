package ru.nsu.fit.directors.establishmentservice.facade;

import java.util.List;

import jakarta.annotation.Nonnull;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestReviewDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.ReviewAnswerRequest;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseReviewDto;
import ru.nsu.fit.directors.establishmentservice.model.Review;

public interface ReviewFacade {
    /**
     * Создать отзыв.
     *
     * @param requestReviewDto информация об отзыве
     * @return отзыв
     */
    @Nonnull
    Review createReview(RequestReviewDto requestReviewDto);

    /**
     * Найти отзывы.
     *
     * @param establishmentId идентификатор заведения
     * @return список отзывов
     */
    @Nonnull
    List<ResponseReviewDto> findReviews(Long establishmentId);

    /**
     * Найти отзывы.
     *
     * @param ids идентификаторы отзывов
     * @return список отзывов
     */
    @Nonnull
    List<ResponseReviewDto> findReviews(List<Long> ids);

    /**
     * Создать ответ на отзыв.
     *
     * @param reviewAnswerRequest данные ответа
     */
    void createAnswer(ReviewAnswerRequest reviewAnswerRequest);

    /**
     * Изменить ответ на отзыв.
     *
     * @param reviewAnswerRequest данные ответа
     */
    void changeAnswer(ReviewAnswerRequest reviewAnswerRequest);
}
