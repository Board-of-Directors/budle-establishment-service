package ru.nsu.fit.directors.establishmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicEstablishmentInfo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentListDto {
    private List<ResponseBasicEstablishmentInfo> establishments;
    private Integer count;
}
