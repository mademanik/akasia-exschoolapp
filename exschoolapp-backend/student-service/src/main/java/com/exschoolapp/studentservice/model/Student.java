package com.exschoolapp.studentservice.model;

import com.exschoolapp.studentservice.model.base.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends BaseJpaEntity {
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    private String address;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade")
    private Grade grade;
}
