package com.exschoolapp.registrationservice.exception;

import com.exschoolapp.registrationservice.helper.ApplicationMessages;
import com.exschoolapp.registrationservice.helper.ResponseHandler;
import com.exschoolapp.registrationservice.helper.ValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException e) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(e.contentUTF8());
        int statusCode = 400;
        String message = e.contentUTF8();
        if (jsonNode.get("status") != null) {
            statusCode = jsonNode.get("status").asInt();
        }
        if (jsonNode.get("message") != null) {
            message = jsonNode.get("message").asText();
        }
        return ResponseHandler.generateResponse(ApplicationMessages.FEIGN_ERROR.getValue(message),
                HttpStatus.valueOf(statusCode), null);
    }
}
