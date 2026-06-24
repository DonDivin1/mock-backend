package com.yourproject.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yourproject.ims.model.Term;
import com.yourproject.ims.repository.TermRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registration")
@CrossOrigin(origins = "*")
public class TermController {

    @Autowired
    private TermRepository termRepository;

    /**
     * Student endpoint: returns the active term ONLY if registration is open.
     * GET /api/v1/registration/term
     */
    @GetMapping("/term")
    public ResponseEntity<?> getActiveTerm() {
        Optional<Term> openTerm = termRepository.findByRegistrationOpenTrue();
        if (openTerm.isPresent()) {
            Term term = openTerm.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", term.getId());
            response.put("active", term.isActive());
            response.put("registrationOpen", term.isRegistrationOpen());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
