package ru.nsu.fit.directors.establishmentservice.service;

import ru.nsu.fit.directors.establishmentservice.dto.ValidTimeDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestWorkingHoursDto;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.WorkingHours;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface WorkingHoursService {
    /**
     * Save provided working hours dto associated with current establishment.
     *
     * @param requestWorkingHoursDtos what we need to save.
     * @param establishment           with what working hours will be associated.
     */
    void saveWorkingHours(Set<RequestWorkingHoursDto> requestWorkingHoursDtos, Establishment establishment);

    /**
     * Function that computes and return valid booking time for establishment.
     *
     * @param establishment for what establishment we need to compute booking times.
     * @return list of valid times dto.
     */
    List<ValidTimeDto> generateValidBookingHours(Establishment establishment);

    /**
     * Удаление рабочих часов из базы данных.
     *
     * @param workingHours рабочие часы для удаления
     */
    void deleteHours(Set<WorkingHours> workingHours);

    List<LocalDateTime> generateValidTime(Establishment establishment);
}
