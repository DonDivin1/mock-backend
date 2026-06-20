package com.yourproject.ims.controller;

import com.yourproject.ims.model.FinanceBalance;
import com.yourproject.ims.repository.FinanceBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/finance")
@CrossOrigin(origins = "*")
public class FinanceController {

    @Autowired
    private FinanceBalanceRepository financeRepository;

    @GetMapping("/student-payments/my-balance")
    public ResponseEntity<?> getMyBalance(@RequestHeader("X-Student-Id") String studentId) {
        return financeRepository.findById(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok(new FinanceBalance(studentId, 0.0)));
    }

    @PutMapping("/admin/update-balance")
    public ResponseEntity<?> manualBalanceUpdate(
            @RequestHeader("X-Student-Id") String studentId,
            @RequestParam Double newBalance) {

        Optional<FinanceBalance> financeOpt = financeRepository.findById(studentId);

        FinanceBalance finance;
        if (financeOpt.isPresent()) {
            finance = financeOpt.get();
            finance.setBalance(newBalance);
        } else {
            finance = new FinanceBalance(studentId, newBalance);
        }

        financeRepository.save(finance);
        return ResponseEntity.ok(finance);
    }
}
