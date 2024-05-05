package ru.nsu.fit.directors.establishmentservice.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Photo;
import ru.nsu.fit.directors.establishmentservice.repository.ImageRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmazonImageServiceImpl implements ImageService {
    private static final String PREFIX = "https://storage.yandexcloud.net";
    private static final String BUCKET_NAME = "dio-test-bucket";
    private final AmazonS3 amazonClient;
    private final ImageRepository imageRepository;

    public void save(String path, MultipartFile image) {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(image.getContentType());
            objectMetadata.setContentLength(image.getSize());
            amazonClient.putObject(BUCKET_NAME, path, image.getInputStream(), objectMetadata);
        } catch (IOException exception) {
            log.error("Cannot save picture with path {} and name {}", path, image.getOriginalFilename());
        }

    }

    public void save(String path, String image) {
        amazonClient.putObject(BUCKET_NAME, path, image);
    }

    @Nonnull
    public String getByKey(String relativePath) {
        return String.join("/", PREFIX, BUCKET_NAME, relativePath);
    }

    @Override
    public void saveImages(Set<PhotoDto> photos, Establishment establishment) {
        for (PhotoDto photo : photos) {
            String filePath = UUID.randomUUID().toString();
            Photo photoEntity = new Photo().setEstablishment(establishment).setFilepath(filePath);
            save(filePath, photo.getImage());
            imageRepository.save(photoEntity);
        }
    }

    @Override
    public void deleteImages(List<String> images) {
        for (String image : images) {
            amazonClient.deleteObject(BUCKET_NAME, image);
        }
    }
}
