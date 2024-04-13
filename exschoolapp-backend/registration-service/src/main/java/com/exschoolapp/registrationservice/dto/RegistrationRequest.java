package com.exschoolapp.registrationservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationRequest {
    private Long studentId;
    private Long extracurricularId;
    private String description;
}
