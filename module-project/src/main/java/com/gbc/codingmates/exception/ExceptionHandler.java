package com.gbc.codingmates.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({CustomException.class})
    protected ResponseEntity handleCustomException(CustomException ex){
        ErrorDto errorDto = new ErrorDto(ex.getErrorCode().getHttpStatus(), ex.getMessage());
        return new ResponseEntity(ex.getErrorCode().getHttpStatus(), HttpStatus.valueOf(ex.getMessage()));
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class})
//    protected ResponseEntity handleServerException(Exception ex){
//
//    }
}
