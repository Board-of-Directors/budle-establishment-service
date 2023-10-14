package ru.nsu.fit.directors.establishmentservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.fit.directors.establishmentservice.dto.SpotDto;
import ru.nsu.fit.directors.establishmentservice.dto.TimelineDto;
import ru.nsu.fit.directors.establishmentservice.enums.DayOfWeek;
import ru.nsu.fit.directors.establishmentservice.exception.EstablishmentNotFoundException;
import ru.nsu.fit.directors.establishmentservice.exception.SpotNotFoundException;
import ru.nsu.fit.directors.establishmentservice.mapper.SpotMapper;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Spot;
import ru.nsu.fit.directors.establishmentservice.model.WorkingHours;
import ru.nsu.fit.directors.establishmentservice.repository.EstablishmentRepository;
import ru.nsu.fit.directors.establishmentservice.repository.SpotRepository;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpotServiceImpl implements SpotService {
    private final SpotRepository spotRepository;
    private final EstablishmentRepository establishmentRepository;
    private final SpotMapper spotMapper;

    @Override
    public List<SpotDto> getSpotsByEstablishment(Long establishmentId) {
        log.info("Getting spots by establishment");
        log.info("EstablishmentID: " + establishmentId);
        Establishment establishment = establishmentRepository.findById(establishmentId).orElseThrow();
        return spotMapper.toListDto(spotRepository.findByEstablishment(establishment));
    }

    @Override
    public SpotDto getSpotById(Long spotId) {
        log.info("Getting spot by id");
        log.info("SpotID: " + spotId);
        return spotMapper.modelToDto(spotRepository.findById(spotId)
            .orElseThrow(() -> new SpotNotFoundException(spotId)));
    }

    @Override
    public void createSpot(Long localId, Long establishmentId) {
        log.info("Saving new spot");
        log.info("LocalID: " + localId);
        log.info("EstablishmentID: " + establishmentId);
        spotRepository.save(
            new Spot(localId, establishmentRepository.getReferenceById(establishmentId))
        );
    }

    @Override
    public void saveSpots(List<Spot> spots, Long establishmentId) {
        spots = spots.stream()
            .peek(spot -> spot.setEstablishment(establishmentRepository.getReferenceById(establishmentId)))
            .toList();
        spotRepository.saveAll(spots);
    }

    @Override
    public TimelineDto getSpotTimeline(Long localId, Long establishmentId) {
        log.info("Getting spot timeline");
        Establishment establishment = establishmentRepository.findById(establishmentId)
            .orElseThrow(() -> new EstablishmentNotFoundException(establishmentId));

        Spot spot = spotRepository.findByEstablishmentAndLocalId(establishment, localId)
            .orElseThrow(() -> new SpotNotFoundException(localId));

        LocalDate dateNow = LocalDate.now();
        String today = dateNow.getDayOfWeek().getDisplayName(
            TextStyle.SHORT,
            new Locale("ru")
        );

        WorkingHours todayHours = establishmentRepository.findWorkingHoursByDay(DayOfWeek.getDayByLittleString(today));
/*
        Set<BookingTimesDto> times = orderRepository.findAllByDateAndEstablishment(
                Date.valueOf(dateNow),
                spot.getEstablishment()
            )
            .stream()
            .map(x -> new BookingTimesDto(x.getStartTime().toString(), x.getEndTime().toString()))
            .collect(Collectors.toSet());

        return new TimelineDto()
            .setStart(todayHours.getStartTime())
            .setEnd(todayHours.getEndTime())
            .setTimes(times);


 */

        return null;
    }
}
