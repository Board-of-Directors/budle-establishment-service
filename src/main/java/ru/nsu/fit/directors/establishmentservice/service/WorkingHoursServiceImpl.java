package ru.nsu.fit.directors.establishmentservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.nsu.fit.directors.establishmentservice.dto.ValidTimeDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestWorkingHoursDto;
import ru.nsu.fit.directors.establishmentservice.enums.DayOfWeek;
import ru.nsu.fit.directors.establishmentservice.mapper.WorkingHoursMapper;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.WorkingHours;
import ru.nsu.fit.directors.establishmentservice.repository.WorkingHoursRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkingHoursServiceImpl implements WorkingHoursService {
    private static final int DAY_COUNT_GENERATED_FOR_BOOKING = 14;
    private final ModelMapper mapper;
    private final WorkingHoursRepository workingHoursRepository;
    private final WorkingHoursMapper workingHoursMapper;
    private final Clock clock;

    @Override
    public void saveWorkingHours(Set<RequestWorkingHoursDto> responseWorkingHoursDto, Establishment establishment) {
        log.info("Saving working hours");
        Map<String, WorkingHours> workingHoursMap = new HashMap<>();
        for (RequestWorkingHoursDto dto : responseWorkingHoursDto) {
            log.info("Saving {}", dto);
            for (String day : dto.getDays()) {
                WorkingHours workingHours = mapper.map(dto, WorkingHours.class);
                workingHours.setDayOfWeek(DayOfWeek.getDayByString(day));
                workingHours.setEstablishment(establishment);
                workingHoursMap.put(
                    workingHours.getDayOfWeek().getTranslate(),
                    workingHours
                );
            }
        }
        workingHoursRepository.saveAll(workingHoursMap.values());
    }

    public List<ValidTimeDto> generateValidBookingHours(Establishment establishment) {
        List<ValidTimeDto> times = new ArrayList<>();
        Set<WorkingHours> workingHours = establishment.getWorkingHours();
        LocalDate currentDate = LocalDate.now(clock);
        for (int dayNumber = 0; dayNumber < DAY_COUNT_GENERATED_FOR_BOOKING; dayNumber++) {
            LocalDate bookDate = currentDate.plusDays(dayNumber);
            ValidTimeDto currentDto = workingHoursMapper.convertFromDateAndTimeToValidTimeDto(bookDate);
            Optional<WorkingHours> currentHours = workingHours.stream()
                .filter(x -> x.getDayOfWeek().getTranslateLittle().equals(currentDto.getDayName()))
                .findFirst();
            if (currentHours.isPresent()) {
                List<String> generatedTimes = generateTimeForDay(dayNumber, currentHours.get());
                if (!generatedTimes.isEmpty()) {
                    currentDto.setTimes(generatedTimes);
                    times.add(currentDto);
                }
            }
        }
        return times;
    }

    private List<String> generateTimeForDay(int dayNumber, WorkingHours currentHours) {
        final int DURATION = 30;
        List<LocalTime> generatedTimes;
        ZoneId zone = ZoneId.of("Asia/Novosibirsk");
        LocalTime now = LocalTime.from(ZonedDateTime.now(zone)).withSecond(0).withNano(0);
        if (dayNumber == 0 && now.isAfter(currentHours.getStartTime())) {
            generatedTimes = workingHoursMapper.generateTimes(
                now.plusMinutes(30 - (now.getMinute() % 30)),
                currentHours.getEndTime(),
                DURATION
            );
        } else {
            generatedTimes = workingHoursMapper.generateTimes(
                currentHours.getStartTime(),
                currentHours.getEndTime(),
                DURATION
            );
        }
        return generatedTimes.stream()
            .map(Objects::toString)
            .toList();
    }

    @Override
    public void deleteHours(Set<WorkingHours> workingHours) {
        workingHoursRepository.deleteAll(workingHours);
    }

    @Override
    public List<LocalDateTime> generateValidTime(Establishment establishment) {
        LocalDate currentDate = LocalDate.now();
        Set<WorkingHours> workingHours = establishment.getWorkingHours();
        return StreamEx.iterate(0, i -> i + 1)
            .limit(DAY_COUNT_GENERATED_FOR_BOOKING)
            .map(currentDate::plusDays)
            .mapToEntry(LocalDate::getDayOfWeek)
            .mapValues(java.time.DayOfWeek::getValue)
            .mapValues(dayNumber -> getWorkingHoursByDay(dayNumber, workingHours))
            .filterValues(Objects::nonNull)
            .map(this::generateTimesForCurrentDay)
            .flatMap(Collection::stream)
            .toList();

    }

    private List<LocalDateTime> generateTimesForCurrentDay(Map.Entry<LocalDate, WorkingHours> entry) {
        LocalDate date = entry.getKey();
        return workingHoursMapper.generateTimes(
                entry.getValue().getStartTime(),
                entry.getValue().getEndTime(),
                30
            ).stream()
            .map(date::atTime)
            .toList();
    }

    private WorkingHours getWorkingHoursByDay(int dayNumber, Set<WorkingHours> workingHours) {
        return workingHours.stream()
            .filter(dayOfWeek -> dayOfWeek.getDayOfWeek().getOrdinal().equals(dayNumber))
            .findFirst()
            .orElse(null);
    }
}
