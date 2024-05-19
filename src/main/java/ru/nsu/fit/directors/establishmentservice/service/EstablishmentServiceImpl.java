package ru.nsu.fit.directors.establishmentservice.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.nsu.fit.directors.establishmentservice.dto.EstablishmentListDto;
import ru.nsu.fit.directors.establishmentservice.dto.ValidTimeDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestEstablishmentDto;
import ru.nsu.fit.directors.establishmentservice.dto.request.RequestGetEstablishmentParameters;
import ru.nsu.fit.directors.establishmentservice.dto.response.BasicEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseBasicEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseExtendedEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseShortEstablishmentInfo;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseSubcategoryDto;
import ru.nsu.fit.directors.establishmentservice.dto.response.ResponseTagDto;
import ru.nsu.fit.directors.establishmentservice.enums.Tag;
import ru.nsu.fit.directors.establishmentservice.exception.ErrorWhileParsingEstablishmentMapException;
import ru.nsu.fit.directors.establishmentservice.exception.EstablishmentAlreadyExistsException;
import ru.nsu.fit.directors.establishmentservice.exception.EstablishmentNotFoundException;
import ru.nsu.fit.directors.establishmentservice.mapper.EstablishmentMapper;
import ru.nsu.fit.directors.establishmentservice.mapper.TagConverter;
import ru.nsu.fit.directors.establishmentservice.model.Category;
import ru.nsu.fit.directors.establishmentservice.model.Establishment;
import ru.nsu.fit.directors.establishmentservice.model.Spot;
import ru.nsu.fit.directors.establishmentservice.repository.EstablishmentRepository;
import ru.nsu.fit.directors.establishmentservice.utils.EnumUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstablishmentServiceImpl implements EstablishmentService {
    private final EstablishmentRepository establishmentRepository;
    private final SpotService spotService;
    private final ImageService imageService;
    private final EstablishmentMapper establishmentMapper;
    private final WorkingHoursService workingHoursService;
    private final TagConverter tagConverter;

    @Nonnull
    @Override
    public EstablishmentListDto getEstablishmentByParams(
        RequestGetEstablishmentParameters parameters,
        Pageable pageable
    ) {
        log.info("Getting establishment by parameters {}", parameters);
        List<ResponseBasicEstablishmentInfo> results = establishmentRepository.findBy(
                toExample(parameters),
                query -> query.project(toBasic()).page(pageable)
            )
            .stream()
            .map(establishmentMapper::toBasic)
            .toList();
        return new EstablishmentListDto(results, results.size());
    }

    @Nonnull
    @SuppressWarnings("unused")
    private List<String> toBasic() {
        return Arrays.stream(BasicEstablishmentInfo.class.getDeclaredFields())
            .map(Field::getName)
            .toList();
    }

    @Nonnull
    private Example<Establishment> toExample(RequestGetEstablishmentParameters parameters) {
        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(
            new Establishment()
                .setCategory(EnumUtils.findEnumByNullable(parameters.category(), Category.class))
                .setHasMap(parameters.hasMap())
                .setHasCardPayment(parameters.hasCardPayment())
                .setName(parameters.name()),
            matcher
        );
    }

    @Nonnull
    @Override
    @Transactional
    public Long createEstablishmentV2(Long ownerId, RequestEstablishmentDto dto) {
        checkEstablishmentExistence(dto);
        Establishment establishment = establishmentMapper.toNewModel(dto);
        establishment.setOwnerId(ownerId);
        return saveEstablishmentDataV2(establishment, dto);
    }

    @Nonnull
    private Long saveEstablishmentDataV2(Establishment establishment, RequestEstablishmentDto dto) {
        Set<Tag> tags = tagConverter.toModel(dto.getTags());
        establishment.setTags(tags);
        Establishment savedEstablishment = establishmentRepository.save(establishment);
        workingHoursService.saveWorkingHours(dto.getWorkingHours(), savedEstablishment);
        imageService.saveImages(dto.getPhotosInput(), savedEstablishment);
        Optional.ofNullable(dto.getMap()).ifPresent(map -> addMap(establishment.getId(), map));
        return savedEstablishment.getId();
    }

    @Nonnull
    @Override
    public List<String> getCategories() {
        return Arrays.stream(Category.values()).map(Category::getValue).toList();
    }

    @Nonnull
    @Override
    public List<ResponseTagDto> getTags() {
        return tagConverter.toResponse(Tag.values());
    }

    @Nonnull
    @Override
    public List<ValidTimeDto> getValidTime(Long establishmentId) {
        Establishment establishment = getEstablishmentById(establishmentId);
        return workingHoursService.generateValidBookingHours(establishment);
    }

    @Nonnull
    @Override
    public List<LocalDateTime> getAlternativeValidTime(Long establishmentId) {
        Establishment establishment = getEstablishmentById(establishmentId);
        return workingHoursService.generateValidTime(establishment);
    }

    @Override
    public void recountRating(Integer score, Establishment establishment) {
        if (establishment.getRating() == null) {
            establishment.setRating(score.floatValue());
        } else {
            int currentReviewsCount = establishment.getReviews().size();
            establishment.setRating(
                (establishment.getRating() * currentReviewsCount + score) / (currentReviewsCount + 1)
            );
            establishmentRepository.save(establishment);
        }
    }

    @Nonnull
    @Override
    public List<ResponseBasicEstablishmentInfo> getEstablishmentsByIds(List<Long> ids) {
        return establishmentRepository.findAllByIdIn(ids).stream()
            .map(establishmentMapper::toBasic)
            .toList();

    }

    @Nonnull
    @Override
    public List<ResponseTagDto> getSpotTags(Long establishmentId) {
        Establishment establishment = getEstablishmentById(establishmentId);
        return tagConverter.toResponse(establishment.getTags());

    }

    @Override
    public void addMap(Long establishmentId, String map) {
        try {
            Establishment establishment = getEstablishmentById(establishmentId);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(map)));
            NodeList elems = document.getDocumentElement().getElementsByTagName("path");
            List<Spot> spots = getSpotList(elems);
            spotService.saveSpots(spots, establishmentId);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            String mapPath = "./images/" + establishmentId + ".svg";
            log.info("Map path was {}", mapPath);
            establishment.setMap(mapPath);
            establishment.setHasMap(true);
            establishmentRepository.save(establishment);
            log.info("Map was saved");
            StreamResult result = new StreamResult(new File(mapPath));
            transformer.transform(new DOMSource(document), result);
        } catch (Exception e) {
            log.warn("Map parsing was exited with exception");
            log.warn(e.getMessage());
            throw new ErrorWhileParsingEstablishmentMapException();
        }
    }

    @Nonnull
    private List<Spot> getSpotList(NodeList elems) {
        return IntStream.range(0, elems.getLength())
            .peek(idx -> ((Element) elems.item(idx)).setAttribute("id", String.valueOf(idx)))
            .mapToObj(idx -> {
                Spot spot = new Spot();
                spot.setLocalId((long) idx);
                return spot;
            })
            .toList();
    }

    @Override
    @Nonnull
    public List<ResponseShortEstablishmentInfo> getEstablishmentsByOwner(Long ownerId, @Nullable String name) {
        return establishmentMapper.toShortInfoList(
            establishmentRepository.findAllByOwnerIdAndNameContainsIgnoreCase(
                ownerId,
                Optional.ofNullable(name).orElse(StringUtils.EMPTY)
            )
        );
    }

    @Nonnull
    @Override
    public ResponseSubcategoryDto getCategoryVariants(String category) {
        Category categoryEnum = Category.getEnumByValue(category);
        return categoryEnum.getVariants();
    }

    @Override
    @Transactional
    public void updateEstablishment(Long establishmentId, RequestEstablishmentDto establishmentDto) {
        Establishment originalEstablishment = getEstablishmentById(establishmentId);
        Establishment establishment = establishmentMapper.toNewModel(establishmentDto);
        establishment.setId(establishmentId);
        deleteEstablishmentHours(originalEstablishment);
        saveEstablishmentDataV2(establishment, establishmentDto);
    }

    private void deleteEstablishmentHours(Establishment originalEstablishment) {
        workingHoursService.deleteHours(originalEstablishment.getWorkingHours());
    }

    @Override
    public void deleteEstablishment(Long establishmentId) {
        Establishment establishment = getEstablishmentById(establishmentId);
        establishmentRepository.delete(establishment);
    }

    @Nonnull
    @Override
    public String getTagByName(String tagName) {
        return tagConverter.toResponse(EnumUtils.parseEnum(tagName, Tag.class)).getImage();
    }

    @Nonnull
    @Override
    public Establishment getEstablishmentById(Long establishmentId) {
        return establishmentRepository.findById(establishmentId)
            .orElseThrow(() -> new EstablishmentNotFoundException(establishmentId));
    }

    @Nonnull
    @Override
    public ResponseExtendedEstablishmentInfo getEstablishmentInfoById(Long establishmentId) {
        return establishmentMapper.toExtended(getEstablishmentById(establishmentId));
    }

    private void checkEstablishmentExistence(RequestEstablishmentDto dto) {
        String address = dto.getAddress();
        String name = dto.getName();
        if (establishmentRepository.existsByAddressAndName(address, name)) {
            throw new EstablishmentAlreadyExistsException(name, address);
        }
    }
}
