package ru.nsu.fit.directors.establishmentservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.mapper.PhotoMapper;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Photo;
import ru.nsu.fit.directors.establishmentservice.repository.ImageRepository;

import java.io.File;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final PhotoMapper photoMapper;

    @Override
    public void saveImages(MultipartFile[] images, Establishment establishment) {
        throw new UnsupportedOperationException();
    }

    public void saveImages(Set<PhotoDto> photosDto, Establishment establishment) {
        log.info("Saving image");
        Set<Photo> photos = photoMapper.toModelSet(photosDto, establishment);
        imageRepository.saveAll(photos);
        log.info("Images saved successfully");
    }

    @Override
    public void deleteImages(List<String> imagesPath) {
        log.info("Deleting images");
        for (String path : imagesPath) {
            File file = new File("./images/" + path);
            if (!file.delete()) {
                log.warn("Deleting image was false");
            }
        }
    }

}
