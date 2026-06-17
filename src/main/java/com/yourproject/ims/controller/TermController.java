package com.yourproject.ims.controller;

import com.yourproject.ims.model.Term;
import com.yourproject.ims.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@CrossOrigin(origins = "*")
public class TermController {

    @Autowired
    private TermService termService;

    @GetMapping("/term")
    public ResponseEntity<Term> getTerm(@RequestParam(required = false) String id) {
        Term term = termService.getTerm(id);
        if (term != null) {
            return ResponseEntity.ok(term);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
