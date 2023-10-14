package ru.nsu.fit.directors.establishmentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSubcategoryDto {
    private List<String> variants;
    private String headerName;
    private String fieldName;
}
