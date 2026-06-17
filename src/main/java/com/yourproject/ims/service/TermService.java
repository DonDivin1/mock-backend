package com.yourproject.ims.service;

import com.yourproject.ims.model.Term;
import com.yourproject.ims.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;

    public Term getTerm(String id) {
        if (id != null && !id.isEmpty()) {
            Optional<Term> term = termRepository.findById(id);
            return term.orElse(null);
        } else {
            Optional<Term> activeTerm = termRepository.findByActiveTrue();
            return activeTerm.orElse(null);
        }
    }
}
