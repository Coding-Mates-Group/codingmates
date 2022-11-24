package com.gbc.codingmates.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@AllArgsConstructor
@Getter
public enum ErrorCode {
    //200 SUCCESS
    SUCCESS(OK, "successful"),

    //201 CREATED(POST)
    PROJECT_CREATED(HttpStatus.CREATED, "project recruitment post successfully created!"),

    //400 BAD REQUEST
    INVALID_PARAMETER(BAD_REQUEST, "check parameter value"),

    //401 UNAUTHORISED
    UNAUTHORISED(UNAUTHORIZED,"please login for authentication"),

    //403 FORBIDDEN
    PROJECT_EDIT_PERMISSION_FORBIDDEN(FORBIDDEN, "you have no permission to edit this project recruitment post"),
    CANDIDATE_EDIT_PERMISSION_FORBIDDEN(FORBIDDEN,"you have no permission to accept/reject candidates for this project recruitment"),

    //404 NOT FOUND
    PROJECT_NOT_FOUND(NOT_FOUND,"no such project found!"),

    //409 CONFLICT
    ALREADY_APPLIED_PROJECT(CONFLICT,"already applied to this project recruitment"),
    ALREADY_EXISTING_PROJECT(CONFLICT, "there is already such an existing project"),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "server error, we are resolving this ASAP"),

    //503 SERVICE UNAVAILABLE
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "temporary server shut-down for maintenance");

    private final HttpStatus httpStatus;
    private final String message;

}
