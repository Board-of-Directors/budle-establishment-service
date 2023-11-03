package ru.nsu.fit.directors.establishmentservice.service;

import ru.nsu.fit.directors.establishmentservice.dto.request.RequestReviewDto;

public interface ReviewService {
    void createReview(RequestReviewDto reviewDto);
}
