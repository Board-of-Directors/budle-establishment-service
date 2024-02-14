package ru.nsu.fit.directors.establishmentservice.facade;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestReviewDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseReviewDto;
import ru.nsu.fit.directors.establishmentservice.mapper.ReviewMapper;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.service.EstablishmentService;
import ru.nsu.fit.directors.establishmentservice.service.ReviewService;

@Service
@RequiredArgsConstructor
public class ReviewFacadeImpl implements ReviewFacade {
    private final ReviewService reviewService;
    private final EstablishmentService establishmentService;
    private final ReviewMapper reviewMapper;

    @Transactional
    @Override
    public void createReview(RequestReviewDto reviewDto) {
        Establishment establishment = establishmentService.getEstablishmentById(reviewDto.establishmentId());
        establishmentService.recountRating(reviewDto.score(), establishment);
        reviewService.save(reviewMapper.toModel(reviewDto, establishment));
    }

    @Nonnull
    @Override
    public List<ResponseReviewDto> findReviews(Long establishmentId) {
        Establishment establishment = establishmentService.getEstablishmentById(establishmentId);
        return reviewService.findReviewsByEstablishment(establishment).stream()
            .map(reviewMapper::toDto)
            .toList();
    }
}
