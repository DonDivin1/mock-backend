package com.yourproject.ims.controller;

import com.yourproject.ims.model.FinanceBalance;
import com.yourproject.ims.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/finance/student-payments")
@CrossOrigin(origins = "*")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/my-balance")
    public ResponseEntity<FinanceBalance> getMyBalance(@RequestParam String studentId) {
        FinanceBalance balance = financeService.getBalance(studentId);
        if (balance != null) {
            return ResponseEntity.ok(balance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
