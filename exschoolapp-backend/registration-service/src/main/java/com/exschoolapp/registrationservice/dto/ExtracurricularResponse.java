package com.exschoolapp.registrationservice.dto;

import com.exschoolapp.registrationservice.dto.base.BaseResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExtracurricularResponse extends BaseResponse {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime registrationStartDate;
    private LocalDateTime registrationEndDate;
    private String location;
    private String description;
    private Integer quota;
    private MentorResponse mentor;

    public ExtracurricularResponse() {
        super();
    }
}
