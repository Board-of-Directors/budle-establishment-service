package ru.nsu.fit.directors.establishmentservice.internal;

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
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestEstablishmentDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseShortEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.service.EstablishmentService;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/internal/establishment", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class InternalEstablishmentController {
    private final EstablishmentService establishmentService;


    @GetMapping
    public List<ResponseBasicEstablishmentInfo> getEstablishmentById(@RequestParam List<Long> ids) {
        return establishmentService.getEstablishmentsByIds(ids);
    }

    /**
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

    /**
     * Get establishment by owner.
     *
     * @param ownerId identifier of the owner.
     * @return list of the owner establishments.
     */

    @GetMapping(value = "/owner")
    public List<ResponseShortEstablishmentInfo> getEstablishmentsByOwner(
        @RequestParam Long ownerId,
        @RequestParam(required = false) String name
    ) {
        return establishmentService.getEstablishmentsByOwner(ownerId, name);
    }

}
