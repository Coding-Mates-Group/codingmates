package com.gbc.codingmates.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ErrorDto {
//    private final LocalDateTime timestamp = LocalDateTime.now();
    private final HttpStatus httpStatus;
    private final String message;

    public static ResponseEntity<ErrorDto> toResponseEntity(ErrorCode errorCode){
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorDto.builder()
                        .httpStatus(errorCode.getHttpStatus())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}
