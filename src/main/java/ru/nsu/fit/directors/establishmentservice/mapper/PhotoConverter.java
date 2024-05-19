package ru.nsu.fit.directors.establishmentservice.mapper;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.model.Photo;
import ru.nsu.fit.directors.establishmentservice.service.ImageService;

@Component
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class PhotoConverter {
    private final ImageService imageService;

    @Nonnull
    public List<PhotoDto> toResponse(Collection<Photo> photos) {
        return photos
            .stream()
            .map(x -> new PhotoDto(imageService.getByKey(x.getFilepath())))
            .toList();
    }
}
