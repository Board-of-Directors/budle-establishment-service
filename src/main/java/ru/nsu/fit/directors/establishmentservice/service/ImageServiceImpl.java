package ru.nsu.fit.directors.establishmentservice.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.ParametersAreNonnullByDefault;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.exception.BaseException;
import ru.nsu.fit.directors.establishmentservice.exception.ImageUploadException;
import ru.nsu.fit.directors.establishmentservice.model.DetachedImage;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Photo;
import ru.nsu.fit.directors.establishmentservice.repository.DetachedImageRepository;
import ru.nsu.fit.directors.establishmentservice.repository.ImageRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class ImageServiceImpl implements ImageService {
    private static final String PREFIX = "https://storage.yandexcloud.net";
    private static final String BUCKET_NAME = "budle-image-bucket";
    private final AmazonS3 amazonClient;
    private final ImageRepository imageRepository;
    private final DetachedImageRepository detachedImageRepository;

    public void save(String path, String image) {
        amazonClient.putObject(BUCKET_NAME, path, image);
    }

    @Nonnull
    public String getByKey(String relativePath) {
        return String.join("/", PREFIX, BUCKET_NAME, relativePath);
    }

    @Override
    public void saveImages(Set<PhotoDto> photos, Establishment establishment) {
        List<Photo> savedPhotos = photos.stream()
            .map(photo -> getByLink(photo.getImage()).setEstablishment(establishment))
            .toList();
        imageRepository.saveAll(savedPhotos);
        log.info("Was saved {}", photos);
    }

    @Override
    public void updateImages(Set<PhotoDto> photosInput, Establishment originalEstablishment) {
        Set<String> actualPhotoUrls = photosInput.stream()
            .map(PhotoDto::getImage)
            .collect(Collectors.toSet());
        deleteAbsent(actualPhotoUrls, originalEstablishment);
        appendImages(actualPhotoUrls, originalEstablishment);
    }

    private void appendImages(Set<String> actualPhotoUrls, Establishment originalEstablishment) {
        Set<String> oldPhotoUrls = originalEstablishment.getPhotos().stream()
            .map(Photo::getFilepath)
            .map(this::getByKey)
            .collect(Collectors.toSet());
        List<Photo> newPhotos = actualPhotoUrls.stream()
            .filter(photoUrl -> !oldPhotoUrls.contains(photoUrl))
            .map(this::getByLink)
            .map(photo -> photo.setEstablishment(originalEstablishment))
            .toList();
        imageRepository.saveAll(newPhotos);
    }

    private void deleteAbsent(Set<String> actualPhotoUrls, Establishment establishment) {
        List<Photo> photosToDelete = establishment.getPhotos().stream()
            .filter(photo -> !actualPhotoUrls.contains(getByKey(photo.getFilepath())))
            .toList();
        imageRepository.deleteAll(photosToDelete);
        photosToDelete.forEach(establishment.getPhotos()::remove);
    }

    @Nonnull
    @Override
    public String uploadImage(MultipartFile image) {
        DetachedImage detachedImage = detachedImageRepository.save(new DetachedImage());
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(image.getContentType());
            objectMetadata.setContentLength(image.getSize());
            amazonClient.putObject(
                BUCKET_NAME,
                detachedImage.getId().toString(),
                image.getInputStream(),
                objectMetadata
            );
            return getByKey(detachedImage.getId().toString());
        } catch (IOException ex) {
            log.error(
                "Cannot save picture with path {} and name {}",
                detachedImage.getId(),
                image.getOriginalFilename()
            );
            throw new ImageUploadException();
        }
    }

    @Nonnull
    @Override
    public DetachedImage getImageByLink(String image) {
        return detachedImageRepository.findById(extractImageId(image)).orElseThrow(
            () -> new BaseException("Изображение не найдено", "ImageNotFound")
        );
    }

    @Nonnull
    @Override
    public Photo getByLink(String link) {
        DetachedImage detachedImage = detachedImageRepository.findById(extractImageId(link)).orElseThrow(
            () -> new BaseException("Изображение не найдено", "ImageNotFound")
        );
        detachedImageRepository.delete(detachedImage);
        return new Photo().setFilepath(detachedImage.getId().toString());
    }

    @Nonnull
    private UUID extractImageId(String link) {
        return UUID.fromString(link.replace(PREFIX + "/", "").replace(BUCKET_NAME + "/", ""));
    }
}
