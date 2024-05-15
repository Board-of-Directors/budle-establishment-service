package ru.nsu.fit.directors.establishmentservice.internal;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestReviewDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseReviewDto;
import ru.nsu.fit.directors.establishmentservice.facade.ReviewFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/internal/review", produces = MediaType.APPLICATION_JSON_VALUE)
public class InternalReviewController {
    private final ReviewFacade reviewFacade;

    @PostMapping
    public Long create(@RequestBody @Valid RequestReviewDto reviewDto) {
        return reviewFacade.createReview(reviewDto).getId();
    }

    @GetMapping
    public List<ResponseReviewDto> get(@RequestParam List<Long> ids) {
        return reviewFacade.findReviews(ids);
    }
}
