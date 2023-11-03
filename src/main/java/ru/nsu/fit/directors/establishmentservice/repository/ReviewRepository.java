package ru.nsu.fit.directors.establishmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.directors.establishmentservice.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
