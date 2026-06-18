package com.yourproject.ims.controller;

import com.yourproject.ims.model.Registration;
import com.yourproject.ims.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
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
        Optional<Registration> registrationOpt = registrationRepository.findByStudentIdAndTermId(studentId, termId);
        if (registrationOpt.isPresent()) {
            Registration reg = registrationOpt.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", reg.getId());
            response.put("studentId", reg.getStudentId());
            response.put("studentName", reg.getStudentName());
            response.put("studentDepartmentCode", reg.getStudentDepartmentCode());
            response.put("studentDepartment", reg.getStudentDepartment());
            response.put("studentFaculty", reg.getStudentFaculty());
            response.put("studentProgram", reg.getStudentProgram());
            response.put("termId", reg.getTermId());
            response.put("createdAt", reg.getCreatedAt());
            response.put("updatedAt", reg.getUpdatedAt());
            response.put("totalFee", reg.getTotalFee());
            response.put("courses", reg.getCourses());

            String validationStatus = reg.getValidationStatus();
            if ("APPROVED".equalsIgnoreCase(validationStatus)) {
                response.put("status", "REGISTERED");
            } else {
                response.put("status", validationStatus);
            }
            response.put("isRegistrationOpen", true);

            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
