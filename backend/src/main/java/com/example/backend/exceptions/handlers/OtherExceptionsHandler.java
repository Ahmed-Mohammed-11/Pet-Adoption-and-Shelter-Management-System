//package com.example.backend.exceptions.handlers;
//
//import com.example.backend.exceptions.exception.Error;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDate;
//
//@ControllerAdvice
//public class OtherExceptionsHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Error> handelOtherExceptions(Exception e) {
//        Error error = Error.builder()
//                .message("Internal Server Error")
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .path(String.valueOf(e.getStackTrace()[0]))
//                .timestamp(LocalDate.now())
//                .build();
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
