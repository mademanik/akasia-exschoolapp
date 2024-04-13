package com.exschoolapp.registrationservice.dto;

import com.exschoolapp.registrationservice.dto.base.BaseResponse;
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
