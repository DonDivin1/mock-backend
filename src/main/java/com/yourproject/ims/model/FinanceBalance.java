package com.yourproject.ims.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "finance_balance")
public class FinanceBalance {

    @Id
    private String studentId;
    private Double balance;

    public FinanceBalance() {}

    public FinanceBalance(String studentId, Double balance) {
        this.studentId = studentId;
        this.balance = balance;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}
