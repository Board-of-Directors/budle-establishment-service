package ru.nsu.fit.directors.establishmentservice.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ResponseMenuCategoryDto extends ShortResponseMenuCategoryDto {
    private List<ResponseMenuCategoryDto> childCategories;
    private List<ResponseProductDto> products;
    private String name;
    private Long id;
}
