package com.exschoolapp.registrationservice.dto;

import com.exschoolapp.registrationservice.dto.base.BaseResponse;
import lombok.Data;

@Data
public class RegistrationResponse extends BaseResponse {
    private Long studentId;
    private StudentResponse student;
    private Long extracurricularId;
    private ExtracurricularResponse extracurricular;
    private String description;

    public RegistrationResponse() {
        super();
    }
}
