package com.example.steepjakarta.domain.datatransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class ApiResponseDTO<T> {
    private final Integer status;
    private String message;

    private T data;

    public ApiResponseDTO(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiResponseDTO(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

}
