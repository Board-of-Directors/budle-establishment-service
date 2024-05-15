package ru.nsu.fit.directors.establishmentservice.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "detached_image")
@Getter
@Setter
@Accessors(chain = true)
public class DetachedImage {
    @Id
    @UuidGenerator
    private UUID id;

    @CreationTimestamp
    private Instant created;
}
