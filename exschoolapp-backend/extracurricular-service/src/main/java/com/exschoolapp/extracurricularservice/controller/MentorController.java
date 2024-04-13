package com.exschoolapp.extracurricularservice.controller;

import com.exschoolapp.extracurricularservice.dto.MentorRequest;
import com.exschoolapp.extracurricularservice.dto.MentorResponse;
import com.exschoolapp.extracurricularservice.helper.ApplicationMessages;
import com.exschoolapp.extracurricularservice.helper.ResponseHandler;
import com.exschoolapp.extracurricularservice.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMentor(@RequestBody MentorRequest mentorRequest) {
        MentorResponse response = mentorService.createMentor(mentorRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.MENTOR_CREATED.getMessage(), HttpStatus.CREATED, response);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String name) {
        List<MentorResponse> responses;
        if (StringUtils.hasLength(name)) {
            responses = mentorService.findAllMentorsByName(name);
        } else {
            responses = mentorService.findAllMentors();
        }
        return ResponseHandler.generateResponse(!responses.isEmpty() ? ApplicationMessages.MENTOR_RETRIEVED.getMessage() :
                ApplicationMessages.MENTOR_DATA_EMPTY.getMessage(), HttpStatus.OK, responses);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findMentorById(@PathVariable("id") Long id) {
        MentorResponse response = mentorService.findMentorById(id);
        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.MENTOR_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.MENTOR_NOT_FOUND
                    .getValue(id.toString()), HttpStatus.NOT_FOUND, response);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteMentorById(@PathVariable("id") Long id) {
        mentorService.deleteMentorById(id);
        return ResponseHandler.generateResponse(ApplicationMessages.MENTOR_DELETED.getMessage(), HttpStatus.OK, null);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMentor(@PathVariable("id") Long id, @RequestBody MentorRequest mentorRequest) {
        MentorResponse response = mentorService.updateMentorById(id, mentorRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.MENTOR_UPDATED.getMessage(), HttpStatus.OK, response);
    }
}
