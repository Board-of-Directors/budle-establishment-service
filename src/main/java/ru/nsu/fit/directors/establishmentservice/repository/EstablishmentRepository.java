package ru.nsu.fit.directors.establishmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.directors.establishmentservice.enums.DayOfWeek;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.WorkingHours;

import java.util.List;


/**
 * Repository, that connects establishment models with database.
 */
@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    /**
     * Get establishment by provided id.
     *
     * @param id of establishment that we are searching for.
     * @return found establishment.
     */

    Establishment getEstablishmentById(Long id);

    /**
     * Checking existence of establishment by name and address.
     *
     * @param address provided address.
     * @param name    provided name.
     * @return true - if establishment exists, false - otherwise.
     */
    Boolean existsByAddressAndName(String address, String name);

    @Query(value = "SELECT wh from Establishment e inner join WorkingHours wh " +
        "on e.id = wh.establishment.id where wh.dayOfWeek = :day order by wh.startTime")
    WorkingHours findWorkingHoursByDay(@Param("day") DayOfWeek day);

    List<Establishment> findAllByOwnerId(Long ownerId);


}
