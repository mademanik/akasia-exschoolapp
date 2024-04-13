package com.exschoolapp.extracurricularservice.model;

import com.exschoolapp.extracurricularservice.model.base.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mentors")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mentor extends BaseJpaEntity {
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    private String address;
    private String phoneNumber;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdDate ASC")
    private List<Extracurricular> extracurriculars = new ArrayList<>();
}
