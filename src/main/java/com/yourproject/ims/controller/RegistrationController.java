package com.yourproject.ims.controller;

import com.yourproject.ims.model.Registration;
import com.yourproject.ims.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@CrossOrigin(origins = "*")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public ResponseEntity<Registration> getRegistration(
            @RequestParam String termId,
            @RequestParam String studentId) {
        Registration registration = registrationService.getStudentRegistration(termId, studentId);
        if (registration != null) {
            return ResponseEntity.ok(registration);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
