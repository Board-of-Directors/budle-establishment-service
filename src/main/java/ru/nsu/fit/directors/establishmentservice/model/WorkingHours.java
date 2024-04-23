package ru.nsu.fit.directors.establishmentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.nsu.fit.directors.establishmentservice.enums.DayOfWeek;

import java.time.LocalTime;

@Entity
@Table(name = "working_hours")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class WorkingHours {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wh_seq")
    @SequenceGenerator(name = "wh_seq", sequenceName = "working_hours_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Establishment establishment;

    private LocalTime startTime;

    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

}
