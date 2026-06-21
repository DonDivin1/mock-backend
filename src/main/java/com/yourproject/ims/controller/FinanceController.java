package com.yourproject.ims.controller;

import com.yourproject.ims.model.FinanceBalance;
import com.yourproject.ims.model.Registration;
import com.yourproject.ims.repository.FinanceBalanceRepository;
import com.yourproject.ims.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/finance/student-payments")
@CrossOrigin(origins = "*")
public class FinanceController {

    @Autowired
    private FinanceBalanceRepository financeRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @GetMapping("/my-balance")
    public ResponseEntity<?> getMyBalance(@RequestHeader("X-Student-Id") String studentId) {
        return financeRepository.findById(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.ok(new FinanceBalance(studentId, 0.0)));
    }

    @PostMapping("/pay")
    public ResponseEntity<?> processPayment(
            @RequestHeader("X-Student-Id") String studentId,
            @RequestParam BigDecimal amount,
            @RequestParam(required = false) String transactionId) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body("Invalid amount");
        }

        Optional<Registration> regOpt = registrationRepository
                .findTopByStudentIdOrderByCreatedAtDesc(studentId);
        if (regOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Double defaultBalance = regOpt.get().getTotalFee() != null
                ? -regOpt.get().getTotalFee().doubleValue()
                : -300000.0;

        FinanceBalance account = financeRepository.findById(studentId)
                .orElse(new FinanceBalance(studentId, defaultBalance));

        account.setBalance(account.getBalance() + amount.doubleValue());
        financeRepository.save(account);

        return ResponseEntity.ok(account);
    }
}
