package com.exschoolapp.extracurricularservice.dto;

import com.exschoolapp.extracurricularservice.model.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MentorRequest {
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
}
