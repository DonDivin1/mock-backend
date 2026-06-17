package com.yourproject.ims.repository;

import com.yourproject.ims.model.FinanceBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceBalanceRepository extends JpaRepository<FinanceBalance, String> {
}
