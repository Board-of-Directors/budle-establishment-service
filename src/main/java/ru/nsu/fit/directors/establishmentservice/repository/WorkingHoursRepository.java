package ru.nsu.fit.directors.establishmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.directors.establishmentservice.model.WorkingHours;

/**
 * Repository, that connects working hours models with database.
 */
public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {
}
