package com.yourproject.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yourproject.ims.model.Term;
import com.yourproject.ims.repository.TermRepository;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registration")
public class TermController {

    @Autowired
    private TermRepository termRepository;

    @GetMapping("/term")
    public ResponseEntity<?> getActiveTerm() {
        Optional<Term> activeTerm = termRepository.findByActiveTrue();
        if (activeTerm.isPresent()) {
            return ResponseEntity.ok(activeTerm.get());
        }
        return ResponseEntity.notFound().build();
    }
}
