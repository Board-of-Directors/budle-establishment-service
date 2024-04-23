package ru.nsu.fit.directors.establishmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.directors.establishmentservice.enums.DayOfWeek;
import ru.nsu.fit.directors.establishmentservice.model.Category;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.WorkingHours;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    Establishment getEstablishmentById(Long id);

    Boolean existsByAddressAndName(String address, String name);

    @Query(value = "SELECT wh from Establishment e inner join WorkingHours wh " +
                   "on e.id = wh.establishment.id where wh.dayOfWeek = :day order by wh.startTime")
    WorkingHours findWorkingHoursByDay(@Param("day") DayOfWeek day);

    List<Establishment> findAllByOwnerIdAndNameContainsIgnoreCase(Long ownerId, String name);

    Optional<Establishment> findByCategoryAndId(Category category, Long id);

    List<Establishment> findAllByIdIn(List<Long> ids);

}
