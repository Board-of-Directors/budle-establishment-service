package ru.nsu.fit.directors.establishmentservice.dto.request;

import lombok.Data;

@Data
public class RequestCategoryDto {
    private String name;
    private Long parentCategoryId;
    private Long establishmentId;
}
