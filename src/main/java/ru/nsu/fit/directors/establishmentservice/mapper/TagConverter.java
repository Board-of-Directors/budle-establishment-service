package ru.nsu.fit.directors.establishmentservice.mapper;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseTagDto;
import ru.nsu.fit.directors.establishmentservice.enums.Tag;
import ru.nsu.fit.directors.establishmentservice.service.ImageWorker;

@Component
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class TagConverter {
    private final ImageWorker imageWorker;

    @Nonnull
    public List<ResponseTagDto> toResponse(Collection<Tag> tags) {
        return tags.stream()
            .map(x -> new ResponseTagDto(x.getTranslate(), imageWorker.getImageFromResource(x.getAssets())))
            .toList();

    }
}
