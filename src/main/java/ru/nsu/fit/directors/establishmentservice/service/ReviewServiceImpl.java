package ru.nsu.fit.directors.establishmentservice.service;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Review;
import ru.nsu.fit.directors.establishmentservice.repository.ReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Nonnull
    @Override
    public List<Review> findReviewsByEstablishment(Establishment establishment) {
        return reviewRepository.findAllByEstablishment(establishment);
    }
}
