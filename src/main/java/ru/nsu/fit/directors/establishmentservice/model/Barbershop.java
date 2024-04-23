package ru.nsu.fit.directors.establishmentservice.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue(value = "barbershop")
public class Barbershop extends Establishment {
    Category category = Category.barbershop;
}
