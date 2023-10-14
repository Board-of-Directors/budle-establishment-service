package ru.nsu.fit.directors.establishmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Spot;

import java.util.List;
import java.util.Optional;

/**
 * Repository, that connects spot models with database.
 */
@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {

    /**
     * Searching for list of all spots that connected with establishment.
     *
     * @param establishment - from what establishment we're searching spots.
     * @return list of all spots.
     */
    List<Spot> findByEstablishment(Establishment establishment);

    Optional<Spot> findByEstablishmentAndLocalId(Establishment establishment, Long localId);
}
