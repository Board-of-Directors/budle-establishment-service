package ru.nsu.fit.directors.establishmentservice.mapper;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseWorkingHoursDto;
import ru.nsu.fit.directors.establishmentservice.model.WorkingHours;

@Component
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class WorkingHoursConverter {
    private final ModelMapper modelMapper;

    @Nonnull
    public List<ResponseWorkingHoursDto> toResponse(Collection<WorkingHours> workingHours) {
        return workingHours.stream()
            .sorted(Comparator.comparing(o -> o.getDayOfWeek().getOrdinal()))
            .map(this::toResponse)
            .toList();

    }

    @Nonnull
    public ResponseWorkingHoursDto toResponse(WorkingHours workingHours) {
        ResponseWorkingHoursDto dto = modelMapper.map(workingHours, ResponseWorkingHoursDto.class);
        dto.setDayOfWeek(workingHours.getDayOfWeek().getTranslate());
        return dto;
    }
}
