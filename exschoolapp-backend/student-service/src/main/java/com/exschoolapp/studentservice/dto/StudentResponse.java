package com.exschoolapp.studentservice.dto;

import com.exschoolapp.studentservice.dto.base.BaseResponse;
import com.exschoolapp.studentservice.model.Gender;
import com.exschoolapp.studentservice.model.Grade;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponse extends BaseResponse {
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private String phoneNumber;
    private Grade grade;

    public StudentResponse() {
        super();
    }
}
