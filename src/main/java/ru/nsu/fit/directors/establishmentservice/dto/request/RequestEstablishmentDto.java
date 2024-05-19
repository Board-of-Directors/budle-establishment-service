package ru.nsu.fit.directors.establishmentservice.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import ru.nsu.fit.directors.establishmentservice.dto.PhotoDto;

import java.util.Set;


@JsonTypeInfo(
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "category",
    use = JsonTypeInfo.Id.NAME,
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = RequestRestaurantDto.class, name = "Рестораны"),
    @JsonSubTypes.Type(value = RequestHotelDto.class, name = "Отели"),
    @JsonSubTypes.Type(value = RequestBarbershopDto.class, name = "Парикмахерские"),
    @JsonSubTypes.Type(value = RequestGameClubDto.class, name = "Игровые клубы")
})
@Data
@SuperBuilder
@Accessors(chain = true)
@NoArgsConstructor
public class RequestEstablishmentDto {
    @NotNull(message = "Имя не может быть не задано.")
    @Size(max = 200, message = "Название заведения не может превышать 200 символов.")
    private String name;
    @NotNull(message = "Описание не может быть не задано.")
    @Size(max = 1000, message = "Описание не может быть длиннее 1000 символов.")
    private String description;
    @NotNull(message = "Информация об адресе не может быть не задана.")
    @Size(max = 200, message = "Адрес не может быть длиннее 200 символов.")
    private String address;
    private Long owner;
    private boolean hasCardPayment;
    private boolean hasMap;
    @NotNull(message = "Категория не может быть не задана.")
    private String category;
    @NotNull(message = "Основное изображение не может быть не задано.")
    private String image;
    @NotNull(message = "Информация о рабочих часах заведения не может быть не задано.")
    @Size(min = 1, max = 7, message = "Дней работы не может быть меньше 1 и больше 7")
    @Valid
    private Set<RequestWorkingHoursDto> workingHours;
    @NotNull(message = "Информация о тэгах заведения не может быть не задана.")
    private Set<RequestTagDto> tags;
    @NotNull(message = "Информация о фотографиях заведения не может быть пустой.")
    private Set<PhotoDto> photosInput;
    private String map;

}
