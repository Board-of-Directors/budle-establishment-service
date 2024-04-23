package ru.nsu.fit.directors.establishmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.directors.establishmentservice.model.WorkingHours;

public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {
}
