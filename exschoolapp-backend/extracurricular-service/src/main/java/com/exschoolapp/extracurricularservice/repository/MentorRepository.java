package com.exschoolapp.extracurricularservice.repository;

import com.exschoolapp.extracurricularservice.model.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByNameContaining(String name);

    Page<Mentor> findByNameContaining(String name, Pageable pageable);
}
