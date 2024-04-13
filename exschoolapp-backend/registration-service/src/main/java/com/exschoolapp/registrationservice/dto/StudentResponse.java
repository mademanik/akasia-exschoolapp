package com.exschoolapp.registrationservice.dto;

import com.exschoolapp.registrationservice.dto.base.BaseResponse;
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
