package ru.nsu.fit.directors.establishmentservice.dto.request;

public record ChangeProductRequest(
    Long productId,
    String name,
    String price,
    String weightG,
    String description,
    Boolean isOnSale
) {
}
