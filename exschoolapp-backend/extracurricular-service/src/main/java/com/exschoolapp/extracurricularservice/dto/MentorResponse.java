package com.exschoolapp.extracurricularservice.dto;

import com.exschoolapp.extracurricularservice.dto.base.BaseResponse;
import com.exschoolapp.extracurricularservice.model.Gender;
import lombok.Data;

@Data
public class MentorResponse extends BaseResponse {
    private String name;
    private String email;
    private Gender gender;
    private String address;
    private String phoneNumber;

    public MentorResponse() {
        super();
    }
}
