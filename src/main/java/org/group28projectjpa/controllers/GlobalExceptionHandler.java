package org.group28projectjpa.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.group28projectjpa.core.validation.IsAlreadyExistException;
import org.group28projectjpa.core.validation.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {
    // Обработка NullPointerException

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handlerNullPointerException(NullPointerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerNotFoundException(NotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IsAlreadyExistException.class)
    public ResponseEntity<String> handlerIsAlreadyExistException(IsAlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String listOfErrors = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return new ResponseEntity<>(listOfErrors, HttpStatus.BAD_REQUEST);
    }
}
