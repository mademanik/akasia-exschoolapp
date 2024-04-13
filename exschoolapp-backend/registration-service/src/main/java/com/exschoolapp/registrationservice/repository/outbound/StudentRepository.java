package com.exschoolapp.registrationservice.repository.outbound;

import com.exschoolapp.registrationservice.dto.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student", url = "${app.student.api-url}")
public interface StudentRepository {

    @GetMapping(value = "")
    ResponseEntity<RestResponse> findAllStudents();

    @GetMapping(value = "/{id}")
    ResponseEntity<RestResponse> findStudentById(@PathVariable("id") Long id);
}
