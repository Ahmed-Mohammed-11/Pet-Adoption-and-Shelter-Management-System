package com.example.backend.Exceptions.handlers;

import com.example.backend.Exceptions.PetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PetNotFoundExceptionHandler {

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<String> handlePetNotFoundException(PetNotFoundException petNotFoundException){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(petNotFoundException.getMessage());
    }
}
