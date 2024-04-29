package ru.nsu.fit.directors.establishmentservice.mapper;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestTagDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseTagDto;
import ru.nsu.fit.directors.establishmentservice.enums.Tag;
import ru.nsu.fit.directors.establishmentservice.service.ImageWorker;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TagMapper {
    private final ImageWorker imageWorker;

    @Nonnull
    public ResponseTagDto toDto(Tag tag) {
        ResponseTagDto tagDto = new ResponseTagDto(tag.translateForSpot, tag.assets);
        tagDto.setImage(imageWorker.getImageFromResource(tagDto.getImage()));
        return tagDto;
    }

    @Nonnull
    public List<ResponseTagDto> toDtoList(Set<Tag> tags) {
        return tags.stream()
            .map(this::toDto)
            .toList();
    }

    @Nonnull
    public ResponseTagDto modelToTagDto(Tag tag) {
        ResponseTagDto tagDto = new ResponseTagDto(tag.translate, tag.assets);
        tagDto.setImage(imageWorker.getImageFromResource(tagDto.getImage()));
        return tagDto;
    }

    @Nonnull
    public List<ResponseTagDto> toDtoList(Tag[] tags) {
        return Arrays.stream(tags)
            .map(this::modelToTagDto)
            .toList();
    }

    @Nonnull
    public Set<Tag> toModelSet(Set<RequestTagDto> tagDtoSet) {
        return tagDtoSet.stream()
            .map(x -> Tag.parseEnum(x.getName()))
            .collect(Collectors.toSet());
    }

}
