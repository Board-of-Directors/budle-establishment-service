package ru.nsu.fit.directors.establishmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.directors.establishmentservice.model.Photo;

public interface ImageRepository extends JpaRepository<Photo, Long> {
}
