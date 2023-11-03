package ru.nsu.fit.directors.establishmentservice.internal;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestReviewDto;
import ru.nsu.fit.directors.establishmentservice.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/internal/review")
public class InternalReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public void create(@RequestBody @Valid RequestReviewDto reviewDto) {
        reviewService.createReview(reviewDto);
    }
}
