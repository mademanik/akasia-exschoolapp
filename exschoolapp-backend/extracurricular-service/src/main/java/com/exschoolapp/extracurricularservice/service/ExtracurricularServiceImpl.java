package com.exschoolapp.extracurricularservice.service;

import com.exschoolapp.extracurricularservice.dto.ExtracurricularRequest;
import com.exschoolapp.extracurricularservice.dto.ExtracurricularResponse;
import com.exschoolapp.extracurricularservice.helper.ValidationErrors;
import com.exschoolapp.extracurricularservice.helper.Validations;
import com.exschoolapp.extracurricularservice.mapper.ExtracurricularMapper;
import com.exschoolapp.extracurricularservice.model.Extracurricular;
import com.exschoolapp.extracurricularservice.model.Mentor;
import com.exschoolapp.extracurricularservice.repository.ExtracurricularRepository;
import com.exschoolapp.extracurricularservice.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExtracurricularServiceImpl implements ExtracurricularService {

    @Autowired
    private ExtracurricularRepository extracurricularRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private ExtracurricularMapper extracurricularMapper;

    @Override
    public ExtracurricularResponse createExtracurricular(ExtracurricularRequest createExtracurricularRequest) {
        Extracurricular extracurricular = extracurricularMapper.mapToExtracurricular(createExtracurricularRequest);
        Optional<Mentor> optOfMentor = mentorRepository.findById(createExtracurricularRequest.getMentorId());
        Validations.checkArgument(
                optOfMentor.isPresent(), ValidationErrors.MENTOR_NOT_FOUND);
        Mentor mentor = optOfMentor.get();
        extracurricular.setMentor(mentor);
        extracurricularRepository.save(extracurricular);
        return extracurricularMapper.mapToExtracurricularResponse(extracurricular);
    }

    @Override
    public List<ExtracurricularResponse> findAllExtracurriculars() {
        List<Extracurricular> extracurriculars = extracurricularRepository.findAll();
        return extracurriculars.stream()
                .map(student -> extracurricularMapper.mapToExtracurricularResponse(student))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExtracurricularResponse> findAllExtracurricularsByName(String name) {
        List<Extracurricular> extracurriculars = extracurricularRepository.findByNameContaining(name);
        return extracurriculars.stream()
                .map(student -> extracurricularMapper.mapToExtracurricularResponse(student))
                .collect(Collectors.toList());
    }

    @Override
    public ExtracurricularResponse findExtracurricularById(Long id) {
        Extracurricular extracurricular = extracurricularRepository.findById(id).orElse(null);
        return extracurricularMapper.mapToExtracurricularResponse(extracurricular);
    }

    @Override
    public void deleteExtracurricularById(Long id) {
        Optional<Extracurricular> optOfExtracurricular = extracurricularRepository.findById(id);
        Validations.checkArgument(
                optOfExtracurricular.isPresent(), ValidationErrors.EXTRACURRICULAR_NOT_FOUND);
        Extracurricular extracurricular = optOfExtracurricular.get();
        extracurricularRepository.deleteById(extracurricular.getId());
    }

    @Override
    public ExtracurricularResponse updateExtracurricularById(Long id, ExtracurricularRequest updateExtracurricularRequest) {
        Optional<Extracurricular> optOfExtracurricular = extracurricularRepository.findById(id);
        Validations.checkArgument(
                optOfExtracurricular.isPresent(), ValidationErrors.EXTRACURRICULAR_NOT_FOUND);
        Extracurricular extracurricular = optOfExtracurricular.get();
        extracurricularMapper.mapUpdateExtracurricular(updateExtracurricularRequest, extracurricular);
        Optional<Mentor> optOfMentor = mentorRepository.findById(updateExtracurricularRequest.getMentorId());
        Validations.checkArgument(
                optOfMentor.isPresent(), ValidationErrors.MENTOR_NOT_FOUND);
        Mentor mentor = optOfMentor.get();
        extracurricular.setMentor(mentor);
        extracurricularRepository.save(extracurricular);
        return extracurricularMapper.mapToExtracurricularResponse(extracurricular);
    }

    @Override
    public Page<ExtracurricularResponse> findAllExtracurricularsPagination(Pageable pageable) {
        Page<Extracurricular> extracurriculars = extracurricularRepository.findAll(pageable);
        return extracurriculars.map(student -> extracurricularMapper.mapToExtracurricularResponse(student));
    }

    @Override
    public Page<ExtracurricularResponse> findAllExtracurricularsByNamePagination(Pageable pageable, String name) {
        Page<Extracurricular> extracurriculars = extracurricularRepository.findByNameContaining(name, pageable);
        return extracurriculars.map(student -> extracurricularMapper.mapToExtracurricularResponse(student));
    }
}
