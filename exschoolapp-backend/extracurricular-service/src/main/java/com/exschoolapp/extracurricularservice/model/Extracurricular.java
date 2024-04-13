package com.exschoolapp.extracurricularservice.model;

import com.exschoolapp.extracurricularservice.model.base.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "extracurriculars")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Extracurricular extends BaseJpaEntity {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime registrationStartDate;
    private LocalDateTime registrationEndDate;
    private String location;
    private String description;
    private Integer quota;


    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
}
