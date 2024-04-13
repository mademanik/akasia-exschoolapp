package com.exschoolapp.studentservice.service;

import com.exschoolapp.studentservice.dto.StudentRequest;
import com.exschoolapp.studentservice.dto.StudentResponse;
import com.exschoolapp.studentservice.helper.ValidationErrors;
import com.exschoolapp.studentservice.helper.Validations;
import com.exschoolapp.studentservice.mapper.StudentMapper;
import com.exschoolapp.studentservice.model.Student;
import com.exschoolapp.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentResponse createStudent(StudentRequest createStudentRequest) {
        Student student = studentMapper.mapToStudent(createStudentRequest);
        studentRepository.save(student);
        return studentMapper.mapToStudentResponse(student);
    }

    @Override
    public List<StudentResponse> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> studentMapper.mapToStudentResponse(student))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponse> findAllStudentsByName(String name) {
        List<Student> students = studentRepository.findByNameContaining(name);
        return students.stream()
                .map(student -> studentMapper.mapToStudentResponse(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse findStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return studentMapper.mapToStudentResponse(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        Optional<Student> optOfStudent = studentRepository.findById(id);
        Validations.checkArgument(
                optOfStudent.isPresent(), ValidationErrors.STUDENT_NOT_FOUND);
        Student student = optOfStudent.get();
        studentRepository.deleteById(student.getId());
    }

    @Override
    public StudentResponse updateStudentById(Long id, StudentRequest updateStudentRequest) {
        Optional<Student> optOfStudent = studentRepository.findById(id);
        Validations.checkArgument(
                optOfStudent.isPresent(), ValidationErrors.STUDENT_NOT_FOUND);
        Student student = optOfStudent.get();
        studentMapper.mapUpdateStudent(updateStudentRequest, student);
        studentRepository.save(student);
        return studentMapper.mapToStudentResponse(student);
    }

    @Override
    public Page<StudentResponse> findAllStudentsPagination(Pageable pageable) {
        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(student -> studentMapper.mapToStudentResponse(student));
    }

    @Override
    public Page<StudentResponse> findAllStudentsByNamePagination(Pageable pageable, String name) {
        Page<Student> students = studentRepository.findByNameContaining(name, pageable);
        return students.map(student -> studentMapper.mapToStudentResponse(student));
    }
}
