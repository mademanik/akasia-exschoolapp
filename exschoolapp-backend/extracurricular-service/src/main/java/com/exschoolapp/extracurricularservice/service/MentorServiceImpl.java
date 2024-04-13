package com.exschoolapp.extracurricularservice.service;

import com.exschoolapp.extracurricularservice.dto.MentorRequest;
import com.exschoolapp.extracurricularservice.dto.MentorResponse;
import com.exschoolapp.extracurricularservice.helper.ValidationErrors;
import com.exschoolapp.extracurricularservice.helper.Validations;
import com.exschoolapp.extracurricularservice.mapper.MentorMapper;
import com.exschoolapp.extracurricularservice.model.Mentor;
import com.exschoolapp.extracurricularservice.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentorServiceImpl implements MentorService {

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentorMapper mentorMapper;

    @Override
    public MentorResponse createMentor(MentorRequest createMentorRequest) {
        Mentor mentor = mentorMapper.mapToMentor(createMentorRequest);
        mentorRepository.save(mentor);
        return mentorMapper.mapToMentorResponse(mentor);
    }

    @Override
    public List<MentorResponse> findAllMentors() {
        List<Mentor> mentors = mentorRepository.findAll();
        return mentors.stream()
                .map(student -> mentorMapper.mapToMentorResponse(student))
                .collect(Collectors.toList());
    }

    @Override
    public List<MentorResponse> findAllMentorsByName(String name) {
        List<Mentor> mentors = mentorRepository.findByNameContaining(name);
        return mentors.stream()
                .map(student -> mentorMapper.mapToMentorResponse(student))
                .collect(Collectors.toList());
    }

    @Override
    public MentorResponse findMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id).orElse(null);
        return mentorMapper.mapToMentorResponse(mentor);
    }

    @Override
    public void deleteMentorById(Long id) {
        Optional<Mentor> optOfMentor = mentorRepository.findById(id);
        Validations.checkArgument(
                optOfMentor.isPresent(), ValidationErrors.MENTOR_NOT_FOUND);
        Mentor mentor = optOfMentor.get();
        mentorRepository.deleteById(mentor.getId());
    }

    @Override
    public MentorResponse updateMentorById(Long id, MentorRequest updateMentorRequest) {
        Optional<Mentor> optOfMentor = mentorRepository.findById(id);
        Validations.checkArgument(
                optOfMentor.isPresent(), ValidationErrors.MENTOR_NOT_FOUND);
        Mentor mentor = optOfMentor.get();
        mentorMapper.mapUpdateMentor(updateMentorRequest, mentor);
        mentorRepository.save(mentor);
        return mentorMapper.mapToMentorResponse(mentor);
    }

    @Override
    public Page<MentorResponse> findAllMentorsPagination(Pageable pageable) {
        Page<Mentor> mentors = mentorRepository.findAll(pageable);
        return mentors.map(student -> mentorMapper.mapToMentorResponse(student));
    }

    @Override
    public Page<MentorResponse> findAllMentorsByNamePagination(Pageable pageable, String name) {
        Page<Mentor> extracurriculars = mentorRepository.findByNameContaining(name, pageable);
        return extracurriculars.map(student -> mentorMapper.mapToMentorResponse(student));
    }
}
