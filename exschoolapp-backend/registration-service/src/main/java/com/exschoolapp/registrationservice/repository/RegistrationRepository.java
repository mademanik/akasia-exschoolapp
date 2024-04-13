package com.exschoolapp.registrationservice.repository;

import com.exschoolapp.registrationservice.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    @Query("select count(e) from #{#entityName} e where e.extracurricularId = ?1")
    Integer countRegistrationByExtracurricularId(Long id);

    @Query("select e from #{#entityName} e where e.studentId = ?1 and e.extracurricularId = ?2")
    Optional<Registration> findRegistrationByStudentIdAndExtracurricularId(Long studentId, Long extracurricularId);
}
