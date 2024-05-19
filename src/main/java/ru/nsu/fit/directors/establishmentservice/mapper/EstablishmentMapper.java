package ru.nsu.fit.directors.establishmentservice.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestEstablishmentDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseShortEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.exception.ErrorWhileParsingEstablishmentMapException;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.service.EstablishmentFactory;
import ru.nsu.fit.directors.establishmentservice.service.ImageService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@Slf4j
@Component
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class EstablishmentMapper {
    private final ModelMapper modelMapper;
    private final EstablishmentFactory establishmentFactory;
    private final ImageService amazonImageServiceImpl;
    private final WorkingHoursConverter workingHoursConverter;
    private final TagConverter tagConverter;
    private final PhotoConverter photoConverter;

    @Nonnull
    public ResponseExtendedEstablishmentInfo toExtended(Establishment establishment) {
        Class<? extends ResponseShortEstablishmentInfo> classOfDto = establishmentFactory
            .getEstablishmentDto(establishment.getCategory().toString(), "extended");

        ResponseExtendedEstablishmentInfo responseEstablishmentInfo =
            (ResponseExtendedEstablishmentInfo) modelMapper.map(establishment, classOfDto);

        responseEstablishmentInfo.setWorkingHours(workingHoursConverter.toResponse(establishment.getWorkingHours()));
        responseEstablishmentInfo.setTags(tagConverter.toResponse(establishment.getTags()));
        responseEstablishmentInfo.setPhotos(photoConverter.toResponse(establishment.getPhotos()));
        responseEstablishmentInfo.setImage(amazonImageServiceImpl.getByKey(establishment.getImage()));
        responseEstablishmentInfo.setMap(getMap(establishment));
        return responseEstablishmentInfo;

    }

    @Nonnull
    public ResponseBasicEstablishmentInfo toBasic(Establishment establishment) {
        Class<? extends ResponseShortEstablishmentInfo> classOfDto = establishmentFactory
            .getEstablishmentDto(establishment.getCategory().toString(), "basic");

        ResponseBasicEstablishmentInfo establishmentDto =
            (ResponseBasicEstablishmentInfo) modelMapper.map(establishment, classOfDto);

        establishmentDto.setImage(amazonImageServiceImpl.getByKey(establishment.getImage()));
        return establishmentDto;
    }

    @Nonnull
    public ResponseShortEstablishmentInfo toShortInfo(Establishment establishment) {
        return modelMapper.map(establishment, ResponseShortEstablishmentInfo.class);
    }

    @Nonnull
    public List<ResponseShortEstablishmentInfo> toShortInfoList(List<Establishment> establishmentList) {
        return establishmentList.stream().map(this::toShortInfo).toList();
    }

    @Nullable
    private String getMap(Establishment establishment) {
        if (!establishment.getHasMap()) {
            return null;
        }
        try {
            BufferedReader mapXml = new BufferedReader(new FileReader(establishment.getMap()));
            StringBuilder builder = new StringBuilder();
            while (mapXml.ready()) {
                builder.append(mapXml.readLine());
            }
            return builder.toString();
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new ErrorWhileParsingEstablishmentMapException();
        }
    }

    @Nonnull
    public Establishment toNewModel(RequestEstablishmentDto dto) {
        Establishment establishment = modelMapper.map(
            dto,
            establishmentFactory.getEstablishmentEntity(dto.getCategory())
        );
        establishment.setImage(amazonImageServiceImpl.getImageByLink(dto.getImage()).getId().toString());
        establishment.setWorkingHours(null);
        establishment.setPhotos(null);
        return establishment;
    }
}
