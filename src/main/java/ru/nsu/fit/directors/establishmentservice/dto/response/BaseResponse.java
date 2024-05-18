package ru.nsu.fit.directors.establishmentservice.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseResponse<T> {
    private T result;
    private ResponseException exception;

    public BaseResponse(T result) {
        this.result = result;
    }

    public BaseResponse(String message, String type) {
        this.exception = new ResponseException(message, type);
    }
}

