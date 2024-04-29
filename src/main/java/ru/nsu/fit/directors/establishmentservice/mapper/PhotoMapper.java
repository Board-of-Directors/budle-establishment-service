package ru.nsu.fit.directors.establishmentservice.mapper;

import jakarta.annotation.Nonnull;
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
public class PhotoMapper {
    private final ImageWorker imageWorker;

    @Nonnull
    public Set<Photo> toModelSet(Set<PhotoDto> photoDtos, Establishment establishment) {
        return photoDtos.stream()
            .map(x -> new Photo().setFilepath(imageWorker.saveImage(x.getImage())).setEstablishment(establishment))
            .collect(Collectors.toSet());
    }

}
