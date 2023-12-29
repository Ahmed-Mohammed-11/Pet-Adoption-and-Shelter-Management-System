package com.example.backend.exceptions.exception;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@Builder
public class Error {
    private String message;
    private HttpStatus status;
    private LocalDate timestamp;
    private String path;
}
