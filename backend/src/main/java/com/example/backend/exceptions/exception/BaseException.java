package com.example.backend.exceptions.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data

public class BaseException extends RuntimeException {

    private Error error;

    public BaseException(String message, HttpStatus status) {
        this.error = Error.builder()
                .message(message)
                .status(status)
                .timestamp(LocalDate.now())
                .path(String.valueOf(super.getStackTrace()[0])).build();
    }
}
