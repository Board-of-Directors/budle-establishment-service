package ru.nsu.fit.directors.establishmentservice.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.SpotDto;
import ru.nsu.fit.directors.establishmentservice.model.Spot;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SpotMapper {
    private final ModelMapper modelMapper;

    public SpotDto modelToDto(Spot spot) {
        return modelMapper.map(spot, SpotDto.class);
    }

    public List<SpotDto> toDtoList(List<Spot> spots) {
        return spots.stream()
            .map(spot -> modelMapper.map(spot, SpotDto.class))
            .toList();
    }
}
