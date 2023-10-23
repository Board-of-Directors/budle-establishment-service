package ru.nsu.fit.directors.establishmentservice.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ResponseProductDto {
    private Long id;
    private String name;
    private String price;
    private String weightG;
    private String description;
    private boolean isOnSale;
}
