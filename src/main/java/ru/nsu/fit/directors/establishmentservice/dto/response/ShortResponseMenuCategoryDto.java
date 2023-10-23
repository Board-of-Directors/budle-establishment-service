package ru.nsu.fit.directors.establishmentservice.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ShortResponseMenuCategoryDto {
    private Long id;
    private String name;
    private Long establishmentId;
}
