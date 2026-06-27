package com.yourproject.ims.controller;

import com.yourproject.ims.model.Course;
import com.yourproject.ims.model.Term;
import com.yourproject.ims.repository.CourseRepository;
import com.yourproject.ims.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/academic")
@CrossOrigin(origins = "*")
public class AdminAcademicController {

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private CourseRepository courseRepository;

    // ─────────────────────────────────────────────────────────────
    // TERM MANAGEMENT
    // ─────────────────────────────────────────────────────────────

    /**
     * Get all terms from the local database.
     * GET /api/v1/admin/academic/terms/all
     */
    @GetMapping("/terms/all")
    public ResponseEntity<?> getAllTerms() {
        List<Term> terms = termRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("content", terms);
        response.put("totalElements", terms.size());
        return ResponseEntity.ok(response);
    }

    /**
     * Admin selects a term to manage (does NOT open student registration yet).
     * Uses @RequestParam to avoid URL encoding issues with slashes in term IDs.
     * PUT /api/v1/admin/academic/terms/activate?termId=2025/1
     */
    @PutMapping("/terms/activate")
    public ResponseEntity<?> activateTerm(@RequestParam String termId) {
        // Deactivate all terms first
        List<Term> allTerms = termRepository.findAll();
        for (Term t : allTerms) {
            t.setActive(false);
            termRepository.save(t);
        }

        // Find or create the target term
        Term term = termRepository.findById(termId).orElse(new Term());
        term.setId(termId);
        term.setActive(true);
        term.setRegistrationOpen(true); // Automatically open registration when activating term
        termRepository.save(term);

        Map<String, Object> response = new HashMap<>();
        response.put("termId", termId);
        response.put("active", true);
        response.put("registrationOpen", term.isRegistrationOpen());
        response.put("message", "Term " + termId + " is now the active managed term.");
        return ResponseEntity.ok(response);
    }

    /**
     * Admin opens student registration for the currently active term.
     * PUT /api/v1/admin/academic/terms/open-registration?termId=2025/1
     */
    @PutMapping("/terms/open-registration")
    public ResponseEntity<?> openRegistration(@RequestParam String termId) {
        Optional<Term> optTerm = termRepository.findById(termId);
        if (optTerm.isEmpty()) {
            return ResponseEntity.badRequest().body("Term not found: " + termId);
        }
        Term term = optTerm.get();
        term.setActive(true);
        term.setRegistrationOpen(true);
        termRepository.save(term);

        Map<String, Object> response = new HashMap<>();
        response.put("termId", termId);
        response.put("registrationOpen", true);
        response.put("message", "Registration is now OPEN for students on term " + termId);
        return ResponseEntity.ok(response);
    }

    /**
     * Admin closes student registration (students can no longer register).
     * PUT /api/v1/admin/academic/terms/close-registration
     */
    @PutMapping("/terms/close-registration")
    public ResponseEntity<?> closeRegistration() {
        List<Term> allTerms = termRepository.findAll();
        for (Term t : allTerms) {
            t.setRegistrationOpen(false);
            termRepository.save(t);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("registrationOpen", false);
        response.put("message", "Registration is now CLOSED for all terms.");
        return ResponseEntity.ok(response);
    }

    /**
     * Admin fully closes/exits the managed term (also closes registration).
     * PUT /api/v1/admin/academic/terms/deactivate-all
     */
    @PutMapping("/terms/deactivate-all")
    public ResponseEntity<?> deactivateAllTerms() {
        List<Term> allTerms = termRepository.findAll();
        for (Term t : allTerms) {
            t.setActive(false);
            t.setRegistrationOpen(false);
            termRepository.save(t);
        }
        return ResponseEntity.ok("All terms deactivated. Registration is now CLOSED.");
    }

    /**
     * Get the status of a specific term (active + registrationOpen flags).
     * GET /api/v1/admin/academic/terms/status?termId=2025/1
     */
    @GetMapping("/terms/status")
    public ResponseEntity<?> getTermStatus(@RequestParam String termId) {
        Optional<Term> optTerm = termRepository.findById(termId);
        if (optTerm.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("termId", termId);
            response.put("exists", false);
            response.put("active", false);
            response.put("registrationOpen", false);
            return ResponseEntity.ok(response);
        }
        Term term = optTerm.get();
        Map<String, Object> response = new HashMap<>();
        response.put("termId", termId);
        response.put("exists", true);
        response.put("active", term.isActive());
        response.put("registrationOpen", term.isRegistrationOpen());
        return ResponseEntity.ok(response);
    }

    /**
     * Get the currently active managed term.
     * GET /api/v1/admin/academic/terms/active
     */
    @GetMapping("/terms/active")
    public ResponseEntity<?> getActiveTerm() {
        Optional<Term> activeTerm = termRepository.findByActiveTrue();
        if (activeTerm.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Term term = activeTerm.get();
        Map<String, Object> response = new HashMap<>();
        response.put("termId", term.getId());
        response.put("active", true);
        response.put("registrationOpen", term.isRegistrationOpen());
        return ResponseEntity.ok(response);
    }

    // ─────────────────────────────────────────────────────────────
    // COURSE MANAGEMENT
    // ─────────────────────────────────────────────────────────────

    /**
     * Admin adds a course to a term.
     * POST /api/v1/admin/academic/courses
     */
    @PostMapping("/courses")
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        try {
            Course savedCourse = courseRepository.save(course);
            return ResponseEntity.ok(savedCourse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Admin deletes a course from a term.
     * DELETE /api/v1/admin/academic/courses/{courseId}
     */
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            return ResponseEntity.ok("Course deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Get all courses for a specific term.
     * GET /api/v1/admin/academic/courses?termId=2025/1
     */
    @GetMapping("/courses")
    public ResponseEntity<?> getCoursesByTerm(@RequestParam String termId) {
        List<Course> courses = courseRepository.findByTermId(termId);
        return ResponseEntity.ok(courses);
    }
}