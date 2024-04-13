package com.exschoolapp.registrationservice.model;

import com.exschoolapp.registrationservice.model.base.BaseJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registrations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Registration extends BaseJpaEntity {
    private Long studentId;
    private Long extracurricularId;
    private String description;
}
