package com.exschoolapp.registrationservice.repository.outbound;

import com.exschoolapp.registrationservice.dto.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "extracurricular", url = "${app.extracurricular.api-url}")
public interface ExtracurricularRepository {

    @GetMapping(value = "")
    ResponseEntity<RestResponse> findAllExtracurriculars();

    @GetMapping(value = "")
    ResponseEntity<RestResponse> findAllExtracurriculars(String name);

    @GetMapping(value = "/{id}")
    ResponseEntity<RestResponse> findExtracurricularById(@PathVariable("id") Long id);
}
