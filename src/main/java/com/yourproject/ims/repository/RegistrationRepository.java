package com.yourproject.ims.repository;

import com.yourproject.ims.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, String> {
    Optional<Registration> findByTermIdAndStudentId(String termId, String studentId);
}
