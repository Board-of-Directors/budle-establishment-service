package ru.nsu.fit.directors.establishmentservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.nsu.fit.directors.establishmentservice.enums.CuisineCountry;
import ru.nsu.fit.directors.establishmentservice.enums.Tag;

import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "establishments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category")
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    private Boolean hasMap;
    private Boolean hasCardPayment;
    private Float rating;
    private Integer price;
    @Enumerated(EnumType.STRING)
    @Column(name = "category", insertable = false, updatable = false)
    private Category category;
    private Long ownerId;
    private String image;
    private String map;
    private Integer starsCount;
    @Enumerated(EnumType.STRING)
    private CuisineCountry cuisineCountry;
    @OneToMany(fetch = FetchType.EAGER,
        mappedBy = "establishment",
        cascade = CascadeType.ALL)
    private Set<WorkingHours> workingHours;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "establishment", cascade = CascadeType.ALL)
    private Set<Photo> photos;

    @ElementCollection(targetClass = Tag.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "establishment_tags", joinColumns = @JoinColumn(name = "establishment_id"))
    @Column(name = "tag_name")
    private Set<Tag> tags;
    @OneToMany(mappedBy = "establishment")
    private Set<Review> reviews;

    public Establishment(Category category, Boolean hasMap, Boolean hasCardPayment, String name) {
        this.category = category;
        this.hasMap = hasMap;
        this.hasCardPayment = hasCardPayment;
        this.name = name;
    }
}
