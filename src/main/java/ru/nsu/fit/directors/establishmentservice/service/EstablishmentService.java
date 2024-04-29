package ru.nsu.fit.directors.establishmentservice.service;

import jakarta.annotation.Nonnull;
import ru.nsu.fit.directors.establishmentservice.dto.EstablishmentListDto;
import ru.nsu.fit.directors.establishmentservice.dto.ValidTimeDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestEstablishmentDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestGetEstablishmentParameters;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseShortEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseSubcategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseTagDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;

import java.time.LocalDateTime;
import java.util.List;

public interface EstablishmentService {
    /**
     * Получить заведения по фильтрам.
     *
     * @param parameters фильтры
     * @return список заведений
     */
    @Nonnull
    EstablishmentListDto getEstablishmentByParams(RequestGetEstablishmentParameters parameters);

    /**
     * Создать заведение.
     *
     * @param ownerId идентификатор создателя
     * @param dto     данные заведения
     * @return идентификатор созданного заведения
     */
    @Nonnull
    Long createEstablishment(Long ownerId, RequestEstablishmentDto dto);

    /**
     * Получить все категории.
     *
     * @return список категорий
     */
    @Nonnull
    List<String> getCategories();

    /**
     * Получить тэги.
     *
     * @return список тэгов
     */
    @Nonnull
    List<ResponseTagDto> getTags();

    /**
     * Добавить карту заведения.
     *
     * @param establishmentId идентификатор заведения
     * @param map             карта
     */
    void addMap(Long establishmentId, String map);

    /**
     * Получить доступное время для бронирования.
     *
     * @param establishmentId идентификатор заведения
     * @return список валидных времен
     */
    @Nonnull
    List<ValidTimeDto> getValidTime(Long establishmentId);

    /**
     * Получить доступные тэги места.
     *
     * @param establishmentId идентификатор заведения
     * @return список тэгов мест
     */
    @Nonnull
    List<ResponseTagDto> getSpotTags(Long establishmentId);

    /**
     * Получить заведение по идентификатору.
     *
     * @param establishmentId идентификатор заведения
     * @return модель заведения
     */
    @Nonnull
    Establishment getEstablishmentById(Long establishmentId);

    /**
     * Получить расширенные данные о заведении.
     *
     * @param establishmentId идентификатор заведения
     * @return расширенные данные о заведении
     */
    @Nonnull
    ResponseExtendedEstablishmentInfo getEstablishmentInfoById(Long establishmentId);

    /**
     * Получить заведения по создателю.
     *
     * @param id   идентифкатор создателя
     * @param name название заведения
     * @return список заведений
     */
    @Nonnull
    List<ResponseShortEstablishmentInfo> getEstablishmentsByOwner(Long id, String name);

    /**
     * Получить варианты подкатегорий.
     *
     * @param category категория
     * @return варианты подкатегорий
     */
    @Nonnull
    ResponseSubcategoryDto getCategoryVariants(String category);

    /**
     * Обновить данные заведения.
     *
     * @param establishmentId  идентификатор заведения
     * @param establishmentDto данные заведения
     */
    void updateEstablishment(Long establishmentId, RequestEstablishmentDto establishmentDto);

    /**
     * Удалить заведение.
     *
     * @param establishmentId идентификатор заведения
     */
    void deleteEstablishment(Long establishmentId);

    /**
     * Получить тэг по названию.
     *
     * @param tagName название тэга
     * @return тэг
     */
    @Nonnull
    String getTagByName(String tagName);

    /**
     * Получить альтернативное валидное время брони.
     *
     * @param establishmentId идентификатор заведения
     * @return список валидных времен для бронирования
     */
    @Nonnull
    List<LocalDateTime> getAlternativeValidTime(Long establishmentId);

    /**
     * Пересчитать рейтинг заведения.
     *
     * @param score         новая оценка
     * @param establishment заведение
     */
    void recountRating(Integer score, Establishment establishment);

    /**
     * Получить заведения по идентификаторам.
     *
     * @param ids идентификаторы заведений
     * @return данные заведений
     */
    @Nonnull
    List<ResponseBasicEstablishmentInfo> getEstablishmentsByIds(List<Long> ids);
}
