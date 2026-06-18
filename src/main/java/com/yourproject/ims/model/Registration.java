package com.yourproject.ims.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    private String id;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_department_code")
    private String studentDepartmentCode;

    @Column(name = "student_department")
    private String studentDepartment;

    @Column(name = "student_faculty")
    private String studentFaculty;

    @Column(name = "student_program")
    private String studentProgram;

    @Column(name = "term_id")
    private String termId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "validation_status")
    private String validationStatus;

    @Column(name = "total_fee")
    private Integer totalFee;

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
    public Integer getTotalFee() { return totalFee; }
    public void setTotalFee(Integer totalFee) { this.totalFee = totalFee; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
