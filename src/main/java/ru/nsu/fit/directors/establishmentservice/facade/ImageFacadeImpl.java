package ru.nsu.fit.directors.establishmentservice.facade;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.directors.establishmentservice.service.ImageService;

@Service
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class ImageFacadeImpl implements ImageFacade {
    private final ImageService amazonImageServiceImpl;

    @Nonnull
    @Override
    public String uploadImage(MultipartFile image) {
        return amazonImageServiceImpl.uploadImage(image);
    }
}
