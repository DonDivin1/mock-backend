package com.yourproject.ims.service;

import com.yourproject.ims.model.FinanceBalance;
import com.yourproject.ims.repository.FinanceBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FinanceService {

    @Autowired
    private FinanceBalanceRepository financeRepository;

    public FinanceBalance getBalance(String studentId) {
        Optional<FinanceBalance> balance = financeRepository.findById(studentId);
        return balance.orElse(null);
    }
}
