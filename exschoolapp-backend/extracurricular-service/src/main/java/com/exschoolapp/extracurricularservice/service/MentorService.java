package com.exschoolapp.extracurricularservice.service;

import com.exschoolapp.extracurricularservice.dto.MentorRequest;
import com.exschoolapp.extracurricularservice.dto.MentorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MentorService {
    public MentorResponse createMentor(MentorRequest createMentorRequest);
    public List<MentorResponse> findAllMentors();
    public List<MentorResponse> findAllMentorsByName(String name);
    public MentorResponse findMentorById(Long id);
    public void deleteMentorById(Long id);
    public MentorResponse updateMentorById(Long id, MentorRequest updateMentorRequest);
    public Page<MentorResponse> findAllMentorsPagination(Pageable pageable);
    public Page<MentorResponse> findAllMentorsByNamePagination(Pageable pageable, String name);
}
