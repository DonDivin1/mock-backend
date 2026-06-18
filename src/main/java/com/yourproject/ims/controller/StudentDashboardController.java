package com.yourproject.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yourproject.ims.repository.AcademicRecordRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/common/student")
public class StudentDashboardController {

    @Autowired
    private AcademicRecordRepository academicRepository;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(@RequestParam String studentId) {
        Optional<com.yourproject.ims.model.AcademicRecord> record = academicRepository.findById(studentId);
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> gpaData = new HashMap<>();

        if (record.isPresent()) {
            gpaData.put("cumulativeGPA", record.get().getCumulativeGpa());
            gpaData.put("totalCreditsEarned", record.get().getTotalCreditsEarned());
        } else {
            gpaData.put("cumulativeGPA", 0.0);
            gpaData.put("totalCreditsEarned", 0);
        }

        response.put("gpaData", gpaData);
        return ResponseEntity.ok(response);
    }
}
