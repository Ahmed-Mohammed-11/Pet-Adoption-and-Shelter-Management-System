package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class AdoptionRecordNotFoundException extends BaseException{
    public AdoptionRecordNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
