package com.exschoolapp.studentservice.dto;

import com.exschoolapp.studentservice.model.Gender;
import com.exschoolapp.studentservice.model.Grade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StudentRequest {
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    private Gender gender;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Grade grade;
}
