package com.exschoolapp.extracurricularservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExtracurricularRequest {
    @NotNull
    private String name;
    @NotNull
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @NotNull
    private LocalDateTime registrationStartDate;
    @NotNull
    private LocalDateTime registrationEndDate;
    @NotNull
    private String location;
    @NotNull
    private String description;
    @NotNull
    private Integer quota;
    @NotNull
    private Long mentorId;
}
