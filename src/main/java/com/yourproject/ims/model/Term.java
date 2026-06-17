package com.yourproject.ims.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "terms")
public class Term {

    @Id
    private String id;

    private String year;
    private String semester;
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationStartDate;
    private LocalDate registrationEndDate;

    private Boolean active;
    private Boolean isRegistrationOpen;
    private Boolean isPreregistrationOpen;
    private Boolean isShortSemester;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public LocalDate getRegistrationStartDate() { return registrationStartDate; }
    public void setRegistrationStartDate(LocalDate registrationStartDate) { this.registrationStartDate = registrationStartDate; }
    public LocalDate getRegistrationEndDate() { return registrationEndDate; }
    public void setRegistrationEndDate(LocalDate registrationEndDate) { this.registrationEndDate = registrationEndDate; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public Boolean getIsRegistrationOpen() { return isRegistrationOpen; }
    public void setIsRegistrationOpen(Boolean isRegistrationOpen) { this.isRegistrationOpen = isRegistrationOpen; }
    public Boolean getIsPreregistrationOpen() { return isPreregistrationOpen; }
    public void setIsPreregistrationOpen(Boolean isPreregistrationOpen) { this.isPreregistrationOpen = isPreregistrationOpen; }
    public Boolean getIsShortSemester() { return isShortSemester; }
    public void setIsShortSemester(Boolean isShortSemester) { this.isShortSemester = isShortSemester; }
}
