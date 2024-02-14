package ru.nsu.fit.directors.establishmentservice.service;

import java.util.List;

import jakarta.annotation.Nonnull;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Review;

public interface ReviewService {
    /**
     * Сохранить отзыв.
     *
     * @param review информация об отзыве
     */
    void save(Review review);

    /**
     * Получить список отзывов, относящихся к данному заведению.
     *
     * @param establishment заведение
     * @return список отзывов
     */
    @Nonnull
    List<Review> findReviewsByEstablishment(Establishment establishment);

    /**
     * Получить список отзывов по идентификаторам.
     *
     * @param ids идентификаторы отзывов
     * @return список отзывов
     */
    @Nonnull
    List<Review> findByIds(List<Long> ids);
}
