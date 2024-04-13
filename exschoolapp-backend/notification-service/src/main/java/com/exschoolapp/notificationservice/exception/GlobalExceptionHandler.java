package com.exschoolapp.notificationservice.exception;

import com.exschoolapp.notificationservice.helper.ApplicationMessages;
import com.exschoolapp.notificationservice.helper.ResponseHandler;
import com.exschoolapp.notificationservice.helper.ValidationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        return ResponseHandler.generateResponse(ApplicationMessages.SYSTEM_ERROR.getValue(e.getMessage()),
                HttpStatus.BAD_REQUEST, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException e) {
        return ResponseHandler.generateResponse(ApplicationMessages.SYSTEM_ERROR.getValue(e.getMessage()),
                HttpStatus.BAD_REQUEST, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseHandler.generateResponse(ApplicationMessages.SYSTEM_ERROR.getValue(e.getMessage()),
                HttpStatus.BAD_REQUEST, null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception e) {
        return ResponseHandler.generateResponse(ApplicationMessages.SYSTEM_ERROR.getValue(e.getMessage()),
                HttpStatus.BAD_REQUEST, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleApplicationException(ValidationException e) {
        return ResponseHandler.generateResponse(ApplicationMessages.SYSTEM_ERROR.getValue(e.getMessage()),
                HttpStatus.BAD_REQUEST, null);
    }
}
