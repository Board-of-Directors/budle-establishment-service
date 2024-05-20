package ru.nsu.fit.directors.establishmentservice.mapper;

import java.time.ZoneId;
import java.util.Date;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestReviewDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseReviewDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Review;

@Component
public class ReviewMapper {
    @Nonnull
    public Review toModel(RequestReviewDto reviewDto, Establishment establishment) {
        return new Review().setText(reviewDto.text())
            .setScore(reviewDto.score())
            .setEstablishment(establishment)
            .setUsername(reviewDto.username())
            .setDate(new Date());
    }

    @Nonnull
    public ResponseReviewDto toDto(Review review) {
        return ResponseReviewDto.builder()
            .username(review.getUsername())
            .text(review.getText())
            .score(review.getScore())
            .date(review.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
            .answer(review.getAnswer())
            .build();
    }
}
