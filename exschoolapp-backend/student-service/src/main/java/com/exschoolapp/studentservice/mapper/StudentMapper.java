package com.exschoolapp.studentservice.mapper;

import com.exschoolapp.studentservice.dto.StudentRequest;
import com.exschoolapp.studentservice.dto.StudentResponse;
import com.exschoolapp.studentservice.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student mapToStudent(StudentRequest studentRequest);
    StudentResponse mapToStudentResponse(Student student);
    void mapUpdateStudent(StudentRequest studentRequest, @MappingTarget Student student);
}
