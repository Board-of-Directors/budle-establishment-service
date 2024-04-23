package ru.nsu.fit.directors.establishmentservice.mapper;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.ValidTimeDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class WorkingHoursMapper {

    @Nonnull
    public ValidTimeDto convertFromDateAndTimeToValidTimeDto(LocalDate date) {
        final TextStyle style = TextStyle.SHORT;
        final Locale locale = new Locale("ru");
        return ValidTimeDto.builder()
            .monthName(date.getMonth().getDisplayName(style, locale))
            .dayName(date.getDayOfWeek().getDisplayName(style, locale))
            .dayNumber(String.valueOf(date.getDayOfMonth()))
            .build();
    }

    @Nonnull
    public List<LocalTime> generateTimes(LocalTime start, LocalTime end, int duration) {
        List<LocalTime> times = new ArrayList<>();
        for (LocalTime time = start; time.isBefore(end); time = time.plusMinutes(duration)) {
            times.add(time);
        }
        return times;
    }
}
