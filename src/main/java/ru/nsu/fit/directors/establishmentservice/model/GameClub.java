package ru.nsu.fit.directors.establishmentservice.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue(value = "game_club")
public class GameClub extends Establishment {
    private final Category category = Category.game_club;
}
