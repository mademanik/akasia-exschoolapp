package com.exschoolapp.registrationservice.dto.base;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseResponse {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
}
