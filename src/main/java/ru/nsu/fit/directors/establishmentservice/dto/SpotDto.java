package ru.nsu.fit.directors.establishmentservice.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpotDto {
    private Long id;
    private String tags;
    private String status;
}
