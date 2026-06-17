package com.yourproject.ims.service;

import com.yourproject.ims.model.Registration;
import com.yourproject.ims.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public Registration getStudentRegistration(String termId, String studentId) {
        Optional<Registration> registration = registrationRepository.findByTermIdAndStudentId(termId, studentId);
        return registration.orElse(null);
    }
}
