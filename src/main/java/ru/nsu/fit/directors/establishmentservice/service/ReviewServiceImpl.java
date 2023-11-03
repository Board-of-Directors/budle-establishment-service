package ru.nsu.fit.directors.establishmentservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestReviewDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Review;
import ru.nsu.fit.directors.establishmentservice.repository.ReviewRepository;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final EstablishmentService establishmentService;

    @Override
    @Transactional
    public void createReview(RequestReviewDto reviewDto) {
        Establishment establishment = establishmentService.getEstablishmentById(reviewDto.establishmentId());
        establishmentService.recountRating(reviewDto.score(), establishment);
        reviewRepository.save(
            new Review().setText(reviewDto.text())
                .setScore(reviewDto.score())
                .setEstablishment(establishment)
                .setUserId(reviewDto.userId())
                .setDate(new Date())
        );

    }
}
