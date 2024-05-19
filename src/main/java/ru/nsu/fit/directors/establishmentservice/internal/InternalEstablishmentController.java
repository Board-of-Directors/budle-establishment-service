package ru.nsu.fit.directors.establishmentservice.internal;

import jakarta.validation.Valid;
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
@RequiredArgsConstructor
public class InternalEstablishmentController {
    private final EstablishmentService establishmentService;

    @GetMapping
    public List<ResponseBasicEstablishmentInfo> getEstablishmentById(@RequestParam List<Long> ids) {
        return establishmentService.getEstablishmentsByIds(ids);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createEstablishment(
        @RequestParam Long ownerId,
        @Valid @RequestBody RequestEstablishmentDto requestEstablishmentDto
    ) {
        return establishmentService.createEstablishment(ownerId, requestEstablishmentDto);
    }

    @PostMapping(value = "/v2/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createEstablishmentV2(
        @RequestParam Long ownerId,
        @Valid @RequestBody RequestEstablishmentDto requestEstablishmentDto
    ) {
        return establishmentService.createEstablishmentV2(ownerId, requestEstablishmentDto);
    }

    @PutMapping(value = "/map", consumes = "application/xml")
    public void createMap(@RequestParam Long establishmentId, @RequestBody String map) {
        establishmentService.addMap(establishmentId, map);
    }

    @PutMapping
    public void update(
        @RequestParam Long establishmentId,
        @RequestBody @Valid RequestEstablishmentDto establishmentDto
    ) {
        establishmentService.updateEstablishment(establishmentId, establishmentDto);
    }

    @DeleteMapping
    public void delete(@RequestParam Long establishmentId) {
        establishmentService.deleteEstablishment(establishmentId);
    }

    @GetMapping(value = "/owner")
    public List<ResponseShortEstablishmentInfo> getEstablishmentsByOwner(
        @RequestParam Long ownerId,
        @RequestParam(required = false) String name
    ) {
        return establishmentService.getEstablishmentsByOwner(ownerId, name);
    }

}
