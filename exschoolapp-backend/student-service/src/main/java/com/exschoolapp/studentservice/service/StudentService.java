package com.exschoolapp.studentservice.service;

import com.exschoolapp.studentservice.dto.StudentRequest;
import com.exschoolapp.studentservice.dto.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    public StudentResponse createStudent(StudentRequest createStudentRequest);
    public List<StudentResponse> findAllStudents();
    public List<StudentResponse> findAllStudentsByName(String name);
    public StudentResponse findStudentById(Long id);
    public void deleteStudentById(Long id);
    public StudentResponse updateStudentById(Long id, StudentRequest updateStudentRequest);
    public Page<StudentResponse> findAllStudentsPagination(Pageable pageable);
    public Page<StudentResponse> findAllStudentsByNamePagination(Pageable pageable, String name);
}
