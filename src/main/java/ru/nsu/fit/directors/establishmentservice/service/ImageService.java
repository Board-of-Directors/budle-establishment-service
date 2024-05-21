package ru.nsu.fit.directors.establishmentservice.service;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.model.DetachedImage;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Photo;

@ParametersAreNonnullByDefault
public interface ImageService {
    /**
     * Получить путь до изображения по ключу.
     *
     * @param relativePath ключ
     * @return путь до изображения
     */
    @Nonnull
    String getByKey(String relativePath);

    /**
     * Загрузить фотографию.
     *
     * @param image фотография
     * @return путь до фотографии
     */
    @Nonnull
    String uploadImage(MultipartFile image);

    /**
     * Получить фотографию по ссылке.
     *
     * @param image ссылка
     * @return фотография
     */
    @Nonnull
    DetachedImage getImageByLink(String image);

    /**
     * Получить фотографию по ссылке.
     *
     * @param link ссылка
     * @return фотография
     */
    @Nonnull
    Photo getByLink(String link);

    /**
     * Сохранить фотографии.
     *
     * @param photos        фотографии
     * @param establishment заведение
     */
    void saveImages(Set<PhotoDto> photos, Establishment establishment);

    /**
     * Обновить изображения.
     *
     * @param photosInput           актуальные изображения
     * @param originalEstablishment заведение
     */
    void updateImages(Set<PhotoDto> photosInput, Establishment originalEstablishment);
}
