package ru.nsu.fit.directors.establishmentservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.directors.establishmentservice.dto.EstablishmentListDto;
import ru.nsu.fit.directors.establishmentservice.dto.ValidTimeDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestGetEstablishmentParameters;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseSubcategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseTagDto;
import ru.nsu.fit.directors.establishmentservice.service.EstablishmentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "establishment", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EstablishmentController {
    private final EstablishmentService establishmentService;

    @GetMapping(value = "all")
    public EstablishmentListDto getEstablishments(@Valid RequestGetEstablishmentParameters parameters) {
        return establishmentService.getEstablishmentByParams(parameters);
    }

    @GetMapping
    public ResponseExtendedEstablishmentInfo getEstablishment(@RequestParam Long establishmentId) {
        return establishmentService.getEstablishmentInfoById(establishmentId);
    }

    @GetMapping(value = "/category")
    public List<String> category() {
        return establishmentService.getCategories();
    }

    @GetMapping(value = "/tags")
    public List<ResponseTagDto> tags() {
        return establishmentService.getTags();
    }

    @GetMapping(value = "/tagImage", produces = MediaType.APPLICATION_XML_VALUE)
    public String tagImage(@RequestParam String tagName) {
        return establishmentService.getTagByName(tagName);
    }

    @GetMapping(value = "/time")
    public List<ValidTimeDto> getTime(@RequestParam Long establishmentId) {
        return establishmentService.getValidTime(establishmentId);
    }

    @GetMapping(value = "/internal/time")
    public List<LocalDateTime> getValidTime(@RequestParam Long establishmentId) {
        return establishmentService.getAlternativeValidTime(establishmentId);
    }

    @GetMapping(value = "/spotTags")
    public List<ResponseTagDto> getTags(@RequestParam Long establishmentId) {
        return establishmentService.getSpotTags(establishmentId);
    }

    @GetMapping(value = "/variants")
    public ResponseSubcategoryDto getCategoryVariants(@RequestParam String category) {
        return establishmentService.getCategoryVariants(category);

    }

}
