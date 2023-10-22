package ru.nsu.fit.directors.establishmentservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.directors.establishmentservice.dto.EstablishmentListDto;
import ru.nsu.fit.directors.establishmentservice.dto.ValidTimeDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestEstablishmentDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestGetEstablishmentParameters;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseShortEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseSubcategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseTagDto;
import ru.nsu.fit.directors.establishmentservice.service.EstablishmentService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class, that represents controller of requests, those connected with the establishment part.
 */
@RestController
@Validated
@RequestMapping(value = "establishment", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class EstablishmentController {
    private final EstablishmentService establishmentService;

    /**
     * Get requests for establishments.
     * Can filter establishments by fields, also implemented sorting and pagination.
     *
     * @param parameters - list of get parameters for establishments
     * @return list of establishment dto, list size included.
     */
    @GetMapping(value = "all")
    public EstablishmentListDto getEstablishments(@Valid RequestGetEstablishmentParameters parameters) {
        return establishmentService.getEstablishmentByParams(parameters);
    }

    /**
     * Get request for the establishment.
     *
     * @param establishmentId identifier of the establishment
     * @return the establishment extended info
     */
    @GetMapping
    public ResponseExtendedEstablishmentInfo getEstablishment(@RequestParam Long establishmentId) {
        return establishmentService.getEstablishmentInfoById(establishmentId);
    }

    /**
     * BUSINESS
     * Post request, that creating new establishment with provided fields.
     *
     * @param requestEstablishmentDto - representation of created establishment.
     *                                provides main information of this establishment,
     *                                such as names, description, etc.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createEstablishment(
        @RequestParam Long ownerId,
        @Valid @RequestBody RequestEstablishmentDto requestEstablishmentDto
    ) {
        return establishmentService.createEstablishment(ownerId, requestEstablishmentDto);
    }

    /**
     * Get request, that returned all existing categories in our application.
     *
     * @return list of existed categories.
     */
    @GetMapping(value = "/category")
    public List<String> category() {
        return establishmentService.getCategories();
    }

    /**
     * BUSINESS
     * Get request for tags, that returns all of existing tags in our system.
     *
     * @return list of tags dto.
     */
    @GetMapping(value = "/tags")
    public List<ResponseTagDto> tags() {
        return establishmentService.getTags();
    }

    @GetMapping(value = "/tagImage", produces = MediaType.APPLICATION_XML_VALUE)
    public String tagImage(@RequestParam String tagName) {
        return establishmentService.getTagByName(tagName);
    }

    /**
     * Get valid time for booking request of current establishment.
     *
     * @param establishmentId - from what establishment we need to get valid booking time.
     * @return list of valid time dto.
     */
    @GetMapping(value = "/time")
    public List<ValidTimeDto> getTime(@RequestParam Long establishmentId) {
        return establishmentService.getValidTime(establishmentId);
    }

    @GetMapping(value = "/internal/time")
    public List<LocalDateTime> getValidTime(@RequestParam Long establishmentId) {
        return establishmentService.getAlternativeValidTime(establishmentId);
    }

    /**
     * Get request, that get all existing in our system spot-tags.
     * WARNING: spot-tags weak-connected with establishment-tags.
     *
     * @param establishmentId from what establishment we collect spot-tags.
     * @return list of spot tags.
     */
    @GetMapping(value = "/spotTags")
    public List<ResponseTagDto> getTags(@RequestParam Long establishmentId) {
        return establishmentService.getSpotTags(establishmentId);
    }

    /**
     * Get request, that get all existing variants for current category
     *
     * @param category current category
     * @return variants for categories
     */
    @GetMapping(value = "/variants")
    public ResponseSubcategoryDto getCategoryVariants(@RequestParam String category) {
        return establishmentService.getCategoryVariants(category);

    }

    /**
     * Put request, that process map of establishment and put it to database.
     *
     * @param establishmentId - in what establishment we add map.
     * @param map             - svg document, that represents scheme of the establishment (with table and spots)
     */
    @PutMapping(value = "/map", consumes = "application/xml")
    public void createMap(@RequestParam Long establishmentId, @RequestBody String map) {
        establishmentService.addMap(establishmentId, map);
    }

    /**
     * Put request, that updates establishment with new params.
     *
     * @param establishmentId  index of updated establishment
     * @param establishmentDto new parameters of the establishment
     */

    @PutMapping
    public void update(
        @RequestParam Long establishmentId,
        @RequestBody @Valid RequestEstablishmentDto establishmentDto
    ) {
        establishmentService.updateEstablishment(establishmentId, establishmentDto);
    }

    /**
     * Delete establishment from database.
     *
     * @param establishmentId index of deleted establishment.
     */

    @DeleteMapping
    public void delete(@RequestParam Long establishmentId) {
        establishmentService.deleteEstablishment(establishmentId);
    }

    @GetMapping(value = "/owner")
    public List<ResponseShortEstablishmentInfo> getEstablishmentsByOwner(@RequestParam Long ownerId) {
        return establishmentService.getEstablishmentsByOwner(ownerId);
    }

}
