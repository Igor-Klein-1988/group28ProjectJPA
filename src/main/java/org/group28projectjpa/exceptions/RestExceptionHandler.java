package org.group28projectjpa.exceptions;

import org.group28projectjpa.dto.StandardResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = RestException.class)
    public ResponseEntity<StandardResponseDto> handlerRestException(RestException e) {
        return buildResponse(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardResponseDto> handlerNullPointerException(NullPointerException e) {
        return buildResponse("Null Pointer Exception Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handling All Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponseDto> handleGeneralException(Exception e) {
        return buildResponse("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    ----

    private ResponseEntity<StandardResponseDto> buildResponse(String message, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(StandardResponseDto.builder()
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
