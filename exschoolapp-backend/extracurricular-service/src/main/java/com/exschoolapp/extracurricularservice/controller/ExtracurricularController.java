package com.exschoolapp.extracurricularservice.controller;

import com.exschoolapp.extracurricularservice.dto.ExtracurricularRequest;
import com.exschoolapp.extracurricularservice.dto.ExtracurricularResponse;
import com.exschoolapp.extracurricularservice.helper.ApplicationMessages;
import com.exschoolapp.extracurricularservice.helper.ResponseHandler;
import com.exschoolapp.extracurricularservice.service.ExtracurricularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extracurriculars")
public class ExtracurricularController {

    @Autowired
    private ExtracurricularService extracurricularService;

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createExtracurricular(@RequestBody ExtracurricularRequest extracurricularRequest) {
        ExtracurricularResponse response = extracurricularService.createExtracurricular(extracurricularRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.EXTRACURRICULAR_CREATED.getMessage(), HttpStatus.CREATED, response);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String name) {
        List<ExtracurricularResponse> responses;
        if (StringUtils.hasLength(name)) {
            responses = extracurricularService.findAllExtracurricularsByName(name);
        } else {
            responses = extracurricularService.findAllExtracurriculars();
        }
        return ResponseHandler.generateResponse(!responses.isEmpty() ? ApplicationMessages.EXTRACURRICULAR_RETRIEVED.getMessage() :
                ApplicationMessages.EXTRACURRICULAR_DATA_EMPTY.getMessage(), HttpStatus.OK, responses);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findExtracurricularById(@PathVariable("id") Long id) {
        ExtracurricularResponse response = extracurricularService.findExtracurricularById(id);
        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.EXTRACURRICULAR_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.EXTRACURRICULAR_NOT_FOUND
                    .getValue(id.toString()), HttpStatus.NOT_FOUND, response);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteExtracurricularById(@PathVariable("id") Long id) {
        extracurricularService.deleteExtracurricularById(id);
        return ResponseHandler.generateResponse(ApplicationMessages.EXTRACURRICULAR_DELETED.getMessage(), HttpStatus.OK, null);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateExtracurricular(@PathVariable("id") Long id, @RequestBody ExtracurricularRequest extracurricularRequest) {
        ExtracurricularResponse response = extracurricularService.updateExtracurricularById(id, extracurricularRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.EXTRACURRICULAR_UPDATED.getMessage(), HttpStatus.OK, response);
    }

}
