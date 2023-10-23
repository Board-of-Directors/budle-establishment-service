package ru.nsu.fit.directors.establishmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.MenuCategory;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuCategory, Long> {
    List<MenuCategory> findAllByEstablishment(Establishment establishment);
}
