package ru.nsu.fit.directors.establishmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.directors.establishmentservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
