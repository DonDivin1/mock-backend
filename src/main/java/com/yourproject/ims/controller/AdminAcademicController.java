package com.yourproject.ims.controller;

import com.yourproject.ims.model.Course;
import com.yourproject.ims.model.Term;
import com.yourproject.ims.repository.CourseRepository;
import com.yourproject.ims.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/academic")
@CrossOrigin(origins = "*")
public class AdminAcademicController {

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private CourseRepository courseRepository;

    // 1. ADMIN ACTIVATES A TERM (And deactivates all others)
    @PutMapping("/terms/{termId}/activate")
    public ResponseEntity<?> activateTerm(@PathVariable String termId) {
        List<Term> allTerms = termRepository.findAll();
        for (Term term : allTerms) {
            if (term.getId().equals(termId)) {
                term.setActive(true);
            } else {
                term.setActive(false);
            }
            termRepository.save(term);
        }
        return ResponseEntity.ok("Term " + termId + " is now ACTIVE. Registration is open.");
    }

    // 2. ADMIN ADDS A COURSE TO A TERM
    @PostMapping("/courses")
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        try {
            // The setCredits method inside Course.java will automatically validate the <=4 rule 
            // and calculate the fee (credits * 21300)
            Course savedCourse = courseRepository.save(course);
            return ResponseEntity.ok(savedCourse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}