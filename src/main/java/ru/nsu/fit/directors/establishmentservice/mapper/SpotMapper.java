package ru.nsu.fit.directors.establishmentservice.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.SpotDto;
import ru.nsu.fit.directors.establishmentservice.model.Spot;

import java.util.List;

/**
 * Class, that represent mapper for spots.
 */
@Component
@RequiredArgsConstructor
public class SpotMapper {
    private final ModelMapper modelMapper;

    /**
     * Convert spot model to spot dto.
     *
     * @param spot model that need to convert.
     * @return spot dto with provided by model fields.
     */
    public SpotDto modelToDto(Spot spot) {
        return modelMapper.map(spot, SpotDto.class);
    }

    /**
     * Convert list of spot models to list of spot dto.
     *
     * @param spots - list of spot models.
     * @return list of spot dto.
     */
    public List<SpotDto> toListDto(List<Spot> spots) {
        return spots
            .stream()
            .map(spot -> modelMapper.map(spot, SpotDto.class))
            .toList();
    }
}
