package ru.nsu.fit.directors.establishmentservice.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseReviewDto;
import ru.nsu.fit.directors.establishmentservice.facade.ReviewFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "establishment/review")
public class ReviewController {
    private final ReviewFacade reviewFacade;

    @GetMapping(value = "/all")
    public List<ResponseReviewDto> search(@RequestParam Long establishmentId) {
        return reviewFacade.findReviews(establishmentId);
    }

    @GetMapping
    public List<ResponseReviewDto> get(@RequestParam Long reviewId){
        return reviewFacade.getById(reviewId);
    }
}
