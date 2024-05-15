package ru.nsu.fit.directors.establishmentservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.nsu.fit.directors.establishmentservice.facade.ImageFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/establishment/image")
public class ImageController {
    private final ImageFacade imageFacade;

    @Operation(description = "Загрузка изображения на сервер")
    @PostMapping
    public String uploadImage(@RequestParam MultipartFile image) {
        return imageFacade.uploadImage(image);
    }

}
