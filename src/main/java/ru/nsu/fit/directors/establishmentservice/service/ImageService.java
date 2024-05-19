package ru.nsu.fit.directors.establishmentservice.service;

import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.model.DetachedImage;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Photo;

import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

public interface ImageService {
    /**
     * Сохранить фотографии для заведения.
     *
     * @param establishment заведение
     */
    default void saveImagesV2(Set<PhotoDto> photos, Establishment establishment) {
        throw new UnsupportedOperationException();
    }

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

    /**
     * Загрузить фотографию в хранилище.
     *
     * @return путь до изображенияя
     */
    @Nonnull
    default String uploadImage(MultipartFile image) {
        throw new UnsupportedOperationException();
    }

    /**
     * Получить изображение по ссылке.
     *
     * @param image ссылка на изображение
     * @return изображение
     */
    @Nonnull
    default DetachedImage getImageByLink(String image) {
        throw new UnsupportedOperationException();
    }

    @Nonnull
    default Photo getByLink(String link) {
        throw new UnsupportedOperationException();
    }
}
