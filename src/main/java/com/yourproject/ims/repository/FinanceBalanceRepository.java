package com.yourproject.ims.repository;

import com.yourproject.ims.model.FinanceBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceBalanceRepository extends JpaRepository<FinanceBalance, String> {
}
