package com.yourproject.ims.controller;

import com.yourproject.ims.model.Course;
import com.yourproject.ims.model.Registration;
import com.yourproject.ims.model.Term;
import com.yourproject.ims.repository.CourseRepository;
import com.yourproject.ims.repository.RegistrationRepository;
import com.yourproject.ims.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registration")
@CrossOrigin(origins = "*")
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private TermRepository termRepository;

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

    @GetMapping("/available-courses")
    public ResponseEntity<?> getAvailableCourses() {
        Optional<Term> activeTerm = termRepository.findByActiveTrue();
        if (activeTerm.isEmpty()) {
            return ResponseEntity.badRequest().body("No active registration term found.");
        }
        
        List<Course> courses = courseRepository.findByTermId(activeTerm.get().getId());
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitRegistration(
            @RequestHeader("X-Student-Id") String studentId,
            @RequestBody List<Long> selectedCourseIds) {
            
        Optional<Term> activeTerm = termRepository.findByActiveTrue();
        if (activeTerm.isEmpty()) {
            return ResponseEntity.badRequest().body("Registration is currently closed.");
        }

        String termId = activeTerm.get().getId();
        
        List<Course> selectedCourses = courseRepository.findAllById(selectedCourseIds);
        
        // CLEANED UP FIX: One single line to calculate and convert the fee to an integer
        int totalFee = (int) selectedCourses.stream().mapToDouble(Course::getFee).sum();

        Registration reg = registrationRepository.findByStudentIdAndTermId(studentId, termId)
                .orElse(new Registration());
                
        reg.setId(studentId + "-" + termId); 
        reg.setStudentId(studentId);
        reg.setTermId(termId);
        
        // Set the integer fee
        reg.setTotalFee(totalFee); 
        reg.setValidationStatus("REGISTERED");
        
        // Set the list of courses directly
        reg.setCourses(selectedCourses); 

        registrationRepository.save(reg);

        return ResponseEntity.ok("Registration submitted successfully! Total fee: " + totalFee + " RWF");
    }
}