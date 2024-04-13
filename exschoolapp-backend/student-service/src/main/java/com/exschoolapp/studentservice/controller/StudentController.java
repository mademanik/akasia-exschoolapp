package com.exschoolapp.studentservice.controller;

import com.exschoolapp.studentservice.dto.StudentRequest;
import com.exschoolapp.studentservice.dto.StudentResponse;
import com.exschoolapp.studentservice.helper.ApplicationMessages;
import com.exschoolapp.studentservice.helper.ResponseHandler;
import com.exschoolapp.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createStudent(@RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.createStudent(studentRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.STUDENT_CREATED.getMessage(), HttpStatus.CREATED, response);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAll(@RequestParam(required = false) String name) {
        List<StudentResponse> responses;
        if (StringUtils.hasLength(name)) {
            responses = studentService.findAllStudentsByName(name);
        } else {
            responses = studentService.findAllStudents();
        }
        return ResponseHandler.generateResponse(!responses.isEmpty() ? ApplicationMessages.STUDENT_RETRIEVED.getMessage() :
                ApplicationMessages.STUDENT_DATA_EMPTY.getMessage(), HttpStatus.OK, responses);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findStudentById(@PathVariable("id") Long id) {
        StudentResponse response = studentService.findStudentById(id);
        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.STUDENT_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.STUDENT_NOT_FOUND
                    .getValue(id.toString()), HttpStatus.NOT_FOUND, response);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return ResponseHandler.generateResponse(ApplicationMessages.STUDENT_DELETED.getMessage(), HttpStatus.OK, null);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStudent(@PathVariable("id") Long id, @RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.updateStudentById(id, studentRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.STUDENT_UPDATED.getMessage(), HttpStatus.OK, response);
    }
}
