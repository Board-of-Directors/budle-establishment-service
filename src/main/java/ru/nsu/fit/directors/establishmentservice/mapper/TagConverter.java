package ru.nsu.fit.directors.establishmentservice.mapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestTagDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseTagDto;
import ru.nsu.fit.directors.establishmentservice.enums.Tag;
import ru.nsu.fit.directors.establishmentservice.service.ImageService;
import ru.nsu.fit.directors.establishmentservice.utils.EnumUtils;

@Component
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class TagConverter {
    private final ImageService imageService;

    @Nonnull
    public List<ResponseTagDto> toResponse(Collection<Tag> tags) {
        return tags.stream()
            .map(this::toResponse)
            .toList();

    }

    @Nonnull
    public List<ResponseTagDto> toResponse(Tag[] tags) {
        return Arrays.stream(tags)
            .map(this::toResponse)
            .toList();
    }

    @Nonnull
    public ResponseTagDto toResponse(Tag tag) {
        return new ResponseTagDto(tag.getTranslate(), imageService.getByKey(tag.getAssets()));
    }

    @Nonnull
    public ResponseTagDto toSpotResponse(Tag tag) {
        return new ResponseTagDto(tag.getTranslateForSpot(), imageService.getByKey(tag.getAssets()));
    }

    @Nonnull
    public List<ResponseTagDto> toSpotResponse(Collection<Tag> tags) {
        return tags.stream()
            .map(this::toSpotResponse)
            .toList();
    }

    @Nonnull
    public Set<Tag> toModel(Set<RequestTagDto> tagDtoSet) {
        return tagDtoSet.stream()
            .map(x -> EnumUtils.parseEnum(x.getName(), Tag.class))
            .collect(Collectors.toSet());
    }
}
