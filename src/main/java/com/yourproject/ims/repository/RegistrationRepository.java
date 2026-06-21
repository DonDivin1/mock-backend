package com.yourproject.ims.repository;

import com.yourproject.ims.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, String> {
    Optional<Registration> findByStudentIdAndTermId(String studentId, String termId);
    Optional<Registration> findTopByStudentIdOrderByCreatedAtDesc(String studentId);
}
