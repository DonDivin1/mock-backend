package com.yourproject.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yourproject.ims.model.Registration;
import com.yourproject.ims.repository.RegistrationRepository;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/registration")
    public ResponseEntity<?> getStudentRegistration(
            @RequestParam String studentId,
            @RequestParam String termId) {
        Optional<Registration> registration = registrationRepository.findByStudentIdAndTermId(studentId, termId);
        if (registration.isPresent()) {
            return ResponseEntity.ok(registration.get());
        }
        return ResponseEntity.notFound().build();
    }
}
