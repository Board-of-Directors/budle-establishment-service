package ru.nsu.fit.directors.establishmentservice.service;

import ru.nsu.fit.directors.establishmentservice.dto.EstablishmentListDto;
import ru.nsu.fit.directors.establishmentservice.dto.ValidTimeDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestEstablishmentDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestGetEstablishmentParameters;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseShortEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseSubcategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseTagDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service, that responsible for establishments.
 */

public interface EstablishmentService {
    /**
     * Function, that getting establishment by provided parameters.
     *
     * @param parameters - request parameters (pagination, sorting, additional)
     * @return pageable list of Establishment dto.
     */
    EstablishmentListDto getEstablishmentByParams(
        RequestGetEstablishmentParameters parameters
    );

    /**
     * Function that creates establishment by provided parameters.
     *
     * @param ownerId index of the creator from business service.
     * @param dto     dto with fields of new establishment model.
     * @return created establishment identifier
     */
    Long createEstablishment(Long ownerId, RequestEstablishmentDto dto);

    /**
     * Function, that returns all categories from out system.
     *
     * @return all categories of establishment those we have.
     */
    List<String> getCategories();

    /**
     * Function, that returns all tags from our system.
     *
     * @return all tags of establishment those we have.
     */

    List<ResponseTagDto> getTags();

    /**
     * Function that add map of establishment to current establishment.
     *
     * @param establishmentId to what establishment we need to add map.
     * @param map             string representation of establishment map.
     */

    void addMap(Long establishmentId, String map);

    /**
     * Function, that compute and return all valid time for booking process.
     *
     * @param establishmentId for what establishment we need to compute valid booking time.
     * @return list of all valid booking times for a week.
     */
    List<ValidTimeDto> getValidTime(Long establishmentId);

    /**
     * Function, that returns all spot tags, those have current establishment.
     *
     * @param establishmentId from what establishment we get spot tags.
     * @return list of tag dto.
     */
    List<ResponseTagDto> getSpotTags(Long establishmentId);

    /**
     * Function, that returns establishment by provided id.
     * Or else throw establishment not found exception.
     *
     * @param establishmentId which we are searching for.
     * @return found establishment.
     */

    Establishment getEstablishmentById(Long establishmentId);

    ResponseExtendedEstablishmentInfo getEstablishmentInfoById(Long establishmentId);

    List<ResponseShortEstablishmentInfo> getEstablishmentsByOwner(Long id);

    ResponseSubcategoryDto getCategoryVariants(String category);

    void updateEstablishment(Long establishmentId, RequestEstablishmentDto establishmentDto);

    void deleteEstablishment(Long establishmentId);

    String getTagByName(String tagName);

    List<LocalDateTime> getAlternativeValidTime(Long establishmentId);
}
