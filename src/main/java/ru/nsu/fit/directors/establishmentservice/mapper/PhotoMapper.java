package ru.nsu.fit.directors.establishmentservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Photo;
import ru.nsu.fit.directors.establishmentservice.service.ImageWorker;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class
PhotoMapper {
    private final ImageWorker imageWorker;

    public Set<Photo> convertSetPhotoDtoToModelSet(
        Set<PhotoDto> photoDtos,
        Establishment establishment
    ) {
        return photoDtos
            .stream()
            .map(x -> new Photo(imageWorker.saveImage(x.getImage())))
            .peek((x) -> x.setEstablishment(establishment))
            .collect(Collectors.toSet());
    }

    public Set<PhotoDto> convertModelPhotoSetToDtoSet(Set<Photo> photos) {
        return photos.stream()
            .map((photo) -> new PhotoDto(imageWorker.loadImage(photo.getFilepath())))
            .collect(Collectors.toSet());
    }

}
