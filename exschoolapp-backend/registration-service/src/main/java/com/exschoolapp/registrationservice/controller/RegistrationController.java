package com.exschoolapp.registrationservice.controller;

import com.exschoolapp.registrationservice.dto.RegistrationRequest;
import com.exschoolapp.registrationservice.dto.RegistrationResponse;
import com.exschoolapp.registrationservice.helper.ApplicationMessages;
import com.exschoolapp.registrationservice.helper.ResponseHandler;
import com.exschoolapp.registrationservice.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@Slf4j
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createRegistration(@RequestBody RegistrationRequest registrationRequest) {
        RegistrationResponse response = registrationService.createRegistration(registrationRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.REGISTRATION_CREATED.getMessage(), HttpStatus.CREATED, response);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll() {
        List<RegistrationResponse> responses = registrationService.findAllRegistrations();
        return ResponseHandler.generateResponse(!responses.isEmpty() ? ApplicationMessages.REGISTRATION_RETRIEVED.getMessage() :
                ApplicationMessages.REGISTRATION_DATA_EMPTY.getMessage(), HttpStatus.OK, responses);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findRegistrationById(@PathVariable("id") Long id) {
        RegistrationResponse response = registrationService.findRegistrationById(id);
        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.REGISTRATION_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.REGISTRATION_NOT_FOUND
                    .getValue(id.toString()), HttpStatus.NOT_FOUND, response);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteRegistrationById(@PathVariable("id") Long id) {
        registrationService.deleteRegistrationById(id);
        return ResponseHandler.generateResponse(ApplicationMessages.REGISTRATION_DELETED.getMessage(), HttpStatus.OK, null);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateRegistration(@PathVariable("id") Long id, @RequestBody RegistrationRequest registrationRequest) {
        RegistrationResponse response = registrationService.updateRegistrationById(id, registrationRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.REGISTRATION_UPDATED.getMessage(), HttpStatus.OK, response);
    }
}
