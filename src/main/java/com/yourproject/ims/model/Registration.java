package com.yourproject.ims.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    private String id;

    private String studentId;
    private String studentName;
    private String studentDepartmentCode;
    private String studentDepartment;
    private String studentFaculty;
    private String studentProgram;
    private String termId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String validationStatus;
    private Double totalFee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "registration_courses",
        joinColumns = @JoinColumn(name = "registration_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getStudentDepartmentCode() { return studentDepartmentCode; }
    public void setStudentDepartmentCode(String studentDepartmentCode) { this.studentDepartmentCode = studentDepartmentCode; }
    public String getStudentDepartment() { return studentDepartment; }
    public void setStudentDepartment(String studentDepartment) { this.studentDepartment = studentDepartment; }
    public String getStudentFaculty() { return studentFaculty; }
    public void setStudentFaculty(String studentFaculty) { this.studentFaculty = studentFaculty; }
    public String getStudentProgram() { return studentProgram; }
    public void setStudentProgram(String studentProgram) { this.studentProgram = studentProgram; }
    public String getTermId() { return termId; }
    public void setTermId(String termId) { this.termId = termId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getValidationStatus() { return validationStatus; }
    public void setValidationStatus(String validationStatus) { this.validationStatus = validationStatus; }
    public Double getTotalFee() { return totalFee; }
    public void setTotalFee(Double totalFee) { this.totalFee = totalFee; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
