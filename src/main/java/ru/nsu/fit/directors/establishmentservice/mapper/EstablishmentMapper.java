package ru.nsu.fit.directors.establishmentservice.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestEstablishmentDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseShortEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseTagDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseWorkingHoursDto;
import ru.nsu.fit.directors.establishmentservice.exception.ErrorWhileParsingEstablishmentMapException;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.service.EstablishmentFactory;
import ru.nsu.fit.directors.establishmentservice.service.ImageService;
import ru.nsu.fit.directors.establishmentservice.service.ImageWorker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

@Slf4j
@Component
@RequiredArgsConstructor
public class EstablishmentMapper {
    private final ModelMapper modelMapper;
    private final ImageWorker imageWorker;
    private final EstablishmentFactory establishmentFactory;
    private final ImageService amazonImageServiceImpl;

    public ResponseExtendedEstablishmentInfo toExtended(Establishment establishment) {
        Class<? extends ResponseShortEstablishmentInfo> classOfDto = establishmentFactory
            .getEstablishmentDto(establishment.getCategory().toString(), "extended");

        ResponseExtendedEstablishmentInfo responseEstablishmentInfo =
            (ResponseExtendedEstablishmentInfo) modelMapper.map(establishment, classOfDto);

        responseEstablishmentInfo.setWorkingHours(establishment
            .getWorkingHours()
            .stream()
            .sorted(Comparator.comparing(o -> o.getDayOfWeek().getOrdinal()))
            .map(x -> {
                ResponseWorkingHoursDto dto = modelMapper.map(x, ResponseWorkingHoursDto.class);
                dto.setDayOfWeek(x.getDayOfWeek().getTranslate());
                return dto;
            })
            .collect(Collectors.toList()));

        responseEstablishmentInfo.setTags(establishment
            .getTags()
            .stream()
            .map(x -> new ResponseTagDto(x.getTranslate(), imageWorker.getImageFromResource(x.getAssets())))
            .collect(Collectors.toSet()));

        responseEstablishmentInfo.setPhotos(establishment
            .getPhotos()
            .stream()
            .map(x -> new PhotoDto(imageWorker.loadImage(x.getFilepath())))
            .collect(Collectors.toSet()));

        responseEstablishmentInfo.setImage(imageWorker.loadImage(establishment.getImage()));
        String map = getMap(establishment);
        responseEstablishmentInfo.setMap(map);

        return responseEstablishmentInfo;

    }

    public ResponseBasicEstablishmentInfo toBasic(Establishment establishment) {
        Class<? extends ResponseShortEstablishmentInfo> classOfDto = establishmentFactory
            .getEstablishmentDto(establishment.getCategory().toString(), "basic");

        ResponseBasicEstablishmentInfo establishmentDto =
            (ResponseBasicEstablishmentInfo) modelMapper.map(establishment, classOfDto);

        establishmentDto.setImage(imageWorker.loadImage(establishment.getImage()));
        return establishmentDto;
    }

    public Establishment toModel(RequestEstablishmentDto dto) {
        Establishment establishment = modelMapper.map(
            dto,
            establishmentFactory.getEstablishmentEntity(dto.getCategory())
        );
        establishment.setImage(imageWorker.saveImage(establishment.getImage()));
        establishment.setWorkingHours(null);
        establishment.setPhotos(null);
        return establishment;
    }

    public ResponseShortEstablishmentInfo toShortInfo(Establishment establishment) {
        return modelMapper.map(establishment, ResponseShortEstablishmentInfo.class);
    }

    public List<ResponseShortEstablishmentInfo> toShortInfoList(List<Establishment> establishmentList) {
        return establishmentList.stream().map(this::toShortInfo).toList();
    }

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
