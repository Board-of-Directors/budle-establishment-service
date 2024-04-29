package ru.nsu.fit.directors.establishmentservice.service;

import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;

import java.util.List;
import java.util.Set;

public interface ImageService {

    /**
     * Сохранить фотографии для заведения.
     *
     * @param photos        фотографии
     * @param establishment заведение
     */
    void saveImages(Set<PhotoDto> photos, Establishment establishment);

    /**
     * Удалить фотографии.
     *
     * @param images фотографии
     */
    void deleteImages(List<String> images);
}
