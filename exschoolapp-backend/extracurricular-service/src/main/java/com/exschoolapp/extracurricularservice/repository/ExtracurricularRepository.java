package com.exschoolapp.extracurricularservice.repository;

import com.exschoolapp.extracurricularservice.model.Extracurricular;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtracurricularRepository extends JpaRepository<Extracurricular, Long> {
    List<Extracurricular> findByNameContaining(String name);
    Page<Extracurricular> findByNameContaining(String name, Pageable pageable);
}
