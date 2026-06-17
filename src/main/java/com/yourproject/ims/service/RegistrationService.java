package com.yourproject.ims.service;

import com.yourproject.ims.model.FinanceBalance;
import com.yourproject.ims.model.Registration;
import com.yourproject.ims.repository.FinanceBalanceRepository;
import com.yourproject.ims.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private FinanceBalanceRepository financeRepository;

    @Transactional
    public Registration getStudentRegistration(String termId, String studentId) {
        Optional<Registration> registrationOpt = registrationRepository.findByTermIdAndStudentId(termId, studentId);

        if (registrationOpt.isPresent()) {
            Registration registration = registrationOpt.get();

            if (registration.getTotalFee() != null) {
                Optional<FinanceBalance> financeOpt = financeRepository.findById(studentId);
                if (financeOpt.isEmpty()) {
                    financeRepository.save(new FinanceBalance(studentId, -registration.getTotalFee()));
                }
            }
            return registration;
        }
        return null;
    }
}
