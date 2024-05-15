package ru.nsu.fit.directors.establishmentservice.exception;

public class ImageUploadException extends BaseException {
    public ImageUploadException() {
        super("Возникла проблема при загрузке изображения.", "ImageUploadException");
    }
}
