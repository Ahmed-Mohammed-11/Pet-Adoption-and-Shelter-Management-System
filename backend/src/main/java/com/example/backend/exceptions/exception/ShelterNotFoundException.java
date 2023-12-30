package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class ShelterNotFoundException extends BaseException{

    public ShelterNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
