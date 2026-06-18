package com.yourproject.ims.model;

import jakarta.persistence.*;

@Entity
@Table(name = "academic_records")
public class AcademicRecord {

    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "cumulative_gpa")
    private Double cumulativeGpa;

    @Column(name = "total_credits_earned")
    private Integer totalCreditsEarned;

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public Double getCumulativeGpa() { return cumulativeGpa; }
    public void setCumulativeGpa(Double cumulativeGpa) { this.cumulativeGpa = cumulativeGpa; }
    public Integer getTotalCreditsEarned() { return totalCreditsEarned; }
    public void setTotalCreditsEarned(Integer totalCreditsEarned) { this.totalCreditsEarned = totalCreditsEarned; }
}
