package ru.nsu.fit.directors.establishmentservice.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.fit.directors.establishmentservice.enums.CuisineCountry;

import java.util.List;


@Entity
@Getter
@Setter
@DiscriminatorValue(value = "restaurant")
public class Restaurant extends Establishment {
    private final Category category = Category.restaurant;

    @Enumerated(EnumType.STRING)
    private CuisineCountry cuisineCountry;
    @OneToMany
    @JoinColumn(name = "establishment_id")
    private List<MenuCategory> categories;
}
