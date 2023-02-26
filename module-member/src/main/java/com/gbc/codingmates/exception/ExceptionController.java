package com.gbc.codingmates.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    private ResponseEntity handleException(IllegalArgumentException illegalArgumentException){
        return ResponseEntity.badRequest().body(illegalArgumentException.getMessage());
    }

}
