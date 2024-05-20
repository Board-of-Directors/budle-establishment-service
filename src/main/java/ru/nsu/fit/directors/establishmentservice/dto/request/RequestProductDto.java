package ru.nsu.fit.directors.establishmentservice.dto.request;

import lombok.Data;

@Data
public class RequestProductDto {
    private String name;
    private String price;
    private String weightG;
    private String description;
    private Long establishmentId;
    private Long categoryId;
    private Boolean isOnSale;

}
