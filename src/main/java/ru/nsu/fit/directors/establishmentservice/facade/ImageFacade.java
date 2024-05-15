package ru.nsu.fit.directors.establishmentservice.facade;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.web.multipart.MultipartFile;

@ParametersAreNonnullByDefault
public interface ImageFacade {
    /**
     * Загрузить изображение.
     *
     * @param image изображение
     * @return путь до изображения
     */
    @Nonnull
    String uploadImage(MultipartFile image);
}
