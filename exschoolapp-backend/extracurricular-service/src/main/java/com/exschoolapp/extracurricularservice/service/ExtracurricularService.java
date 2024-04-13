package com.exschoolapp.extracurricularservice.service;

import com.exschoolapp.extracurricularservice.dto.ExtracurricularRequest;
import com.exschoolapp.extracurricularservice.dto.ExtracurricularResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExtracurricularService {
    public ExtracurricularResponse createExtracurricular(ExtracurricularRequest createExtracurricularRequest);
    public List<ExtracurricularResponse> findAllExtracurriculars();
    public List<ExtracurricularResponse> findAllExtracurricularsByName(String name);
    public ExtracurricularResponse findExtracurricularById(Long id);
    public void deleteExtracurricularById(Long id);
    public ExtracurricularResponse updateExtracurricularById(Long id, ExtracurricularRequest updateExtracurricularRequest);
    public Page<ExtracurricularResponse> findAllExtracurricularsPagination(Pageable pageable);
    public Page<ExtracurricularResponse> findAllExtracurricularsByNamePagination(Pageable pageable, String name);
}
