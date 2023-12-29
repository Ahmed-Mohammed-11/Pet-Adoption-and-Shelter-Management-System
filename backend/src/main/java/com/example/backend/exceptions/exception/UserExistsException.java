package com.example.backend.exceptions.exception;

import org.springframework.http.HttpStatus;

public class UserExistsException extends BaseException{
    public UserExistsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
