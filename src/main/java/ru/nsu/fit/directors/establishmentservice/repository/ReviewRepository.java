package ru.nsu.fit.directors.establishmentservice.repository;

import java.util.List;

import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Nonnull
    List<Review> findAllByEstablishment(Establishment establishment);
}
