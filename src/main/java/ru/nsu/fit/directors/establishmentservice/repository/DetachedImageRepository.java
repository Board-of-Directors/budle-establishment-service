package ru.nsu.fit.directors.establishmentservice.repository;

import java.util.UUID;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.directors.establishmentservice.model.DetachedImage;

@ParametersAreNonnullByDefault
public interface DetachedImageRepository extends JpaRepository<DetachedImage, UUID> {
}
