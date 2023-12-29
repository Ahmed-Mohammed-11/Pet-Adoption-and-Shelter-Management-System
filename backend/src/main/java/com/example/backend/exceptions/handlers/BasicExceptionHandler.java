package com.example.backend.exceptions.handlers;

import com.example.backend.exceptions.exception.BaseException;
import com.example.backend.exceptions.exception.Error;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BasicExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Error> handelBasicException(BaseException e) {
        return new ResponseEntity<>(e.getError(), e.getError().getStatus());
    }
}
