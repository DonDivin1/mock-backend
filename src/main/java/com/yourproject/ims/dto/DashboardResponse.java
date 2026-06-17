package com.yourproject.ims.dto;

import com.yourproject.ims.model.Course;
import java.time.LocalDateTime;
import java.util.List;

public class DashboardResponse {

    private GpaData gpaData;
    private List<RecentGrade> recentGrades;
    private Bulletin bulletin;
    private List<Announcement> announcements;
    private RegistrationSummary registrationSummary;
    private int totalPassedCourses;

    public GpaData getGpaData() { return gpaData; }
    public void setGpaData(GpaData gpaData) { this.gpaData = gpaData; }
    public List<RecentGrade> getRecentGrades() { return recentGrades; }
    public void setRecentGrades(List<RecentGrade> recentGrades) { this.recentGrades = recentGrades; }
    public Bulletin getBulletin() { return bulletin; }
    public void setBulletin(Bulletin bulletin) { this.bulletin = bulletin; }
    public List<Announcement> getAnnouncements() { return announcements; }
    public void setAnnouncements(List<Announcement> announcements) { this.announcements = announcements; }
    public RegistrationSummary getRegistrationSummary() { return registrationSummary; }
    public void setRegistrationSummary(RegistrationSummary registrationSummary) { this.registrationSummary = registrationSummary; }
    public int getTotalPassedCourses() { return totalPassedCourses; }
    public void setTotalPassedCourses(int totalPassedCourses) { this.totalPassedCourses = totalPassedCourses; }

    public static class GpaData {
        private double cumulativeGPA;
        private double currentSemesterGPA;
        private int totalCreditsEarned;
        private int totalCreditsAttempted;
        private int currentSemesterCredits;
        private List<SemesterGPA> semesterGPAs;

        public double getCumulativeGPA() { return cumulativeGPA; }
        public void setCumulativeGPA(double cumulativeGPA) { this.cumulativeGPA = cumulativeGPA; }
        public double getCurrentSemesterGPA() { return currentSemesterGPA; }
        public void setCurrentSemesterGPA(double currentSemesterGPA) { this.currentSemesterGPA = currentSemesterGPA; }
        public int getTotalCreditsEarned() { return totalCreditsEarned; }
        public void setTotalCreditsEarned(int totalCreditsEarned) { this.totalCreditsEarned = totalCreditsEarned; }
        public int getTotalCreditsAttempted() { return totalCreditsAttempted; }
        public void setTotalCreditsAttempted(int totalCreditsAttempted) { this.totalCreditsAttempted = totalCreditsAttempted; }
        public int getCurrentSemesterCredits() { return currentSemesterCredits; }
        public void setCurrentSemesterCredits(int currentSemesterCredits) { this.currentSemesterCredits = currentSemesterCredits; }
        public List<SemesterGPA> getSemesterGPAs() { return semesterGPAs; }
        public void setSemesterGPAs(List<SemesterGPA> semesterGPAs) { this.semesterGPAs = semesterGPAs; }
    }

    public static class SemesterGPA {
        private String termId;
        private double semesterGPA;
        private int creditsEarned;
        private int creditsAttempted;

        public String getTermId() { return termId; }
        public void setTermId(String termId) { this.termId = termId; }
        public double getSemesterGPA() { return semesterGPA; }
        public void setSemesterGPA(double semesterGPA) { this.semesterGPA = semesterGPA; }
        public int getCreditsEarned() { return creditsEarned; }
        public void setCreditsEarned(int creditsEarned) { this.creditsEarned = creditsEarned; }
        public int getCreditsAttempted() { return creditsAttempted; }
        public void setCreditsAttempted(int creditsAttempted) { this.creditsAttempted = creditsAttempted; }
    }

    public static class RecentGrade {
        private String courseCode;
        private String courseName;
        private int credits;
        private double grade;
        private double score;
        private String status;
        private String termId;
        private int year;
        private String semester;

        public String getCourseCode() { return courseCode; }
        public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        public int getCredits() { return credits; }
        public void setCredits(int credits) { this.credits = credits; }
        public double getGrade() { return grade; }
        public void setGrade(double grade) { this.grade = grade; }
        public double getScore() { return score; }
        public void setScore(double score) { this.score = score; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getTermId() { return termId; }
        public void setTermId(String termId) { this.termId = termId; }
        public int getYear() { return year; }
        public void setYear(int year) { this.year = year; }
        public String getSemester() { return semester; }
        public void setSemester(String semester) { this.semester = semester; }
    }

    public static class Bulletin {
        private Long id;
        private String department;
        private String departmentCode;
        private String facultyName;
        private String programName;
        private List<CourseDetail> courseDetails;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }
        public String getDepartmentCode() { return departmentCode; }
        public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }
        public String getFacultyName() { return facultyName; }
        public void setFacultyName(String facultyName) { this.facultyName = facultyName; }
        public String getProgramName() { return programName; }
        public void setProgramName(String programName) { this.programName = programName; }
        public List<CourseDetail> getCourseDetails() { return courseDetails; }
        public void setCourseDetails(List<CourseDetail> courseDetails) { this.courseDetails = courseDetails; }
    }

    public static class CourseDetail {
        private String code;
        private String name;
        private int credits;
        private int expectedTerm;
        private boolean generalCourse;

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getCredits() { return credits; }
        public void setCredits(int credits) { this.credits = credits; }
        public int getExpectedTerm() { return expectedTerm; }
        public void setExpectedTerm(int expectedTerm) { this.expectedTerm = expectedTerm; }
        public boolean isGeneralCourse() { return generalCourse; }
        public void setGeneralCourse(boolean generalCourse) { this.generalCourse = generalCourse; }
    }

    public static class Announcement {
        private Long id;
        private String postedBy;
        private String title;
        private String type;
        private String content;
        private String destination;
        private LocalDateTime date;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getPostedBy() { return postedBy; }
        public void setPostedBy(String postedBy) { this.postedBy = postedBy; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public String getDestination() { return destination; }
        public void setDestination(String destination) { this.destination = destination; }
        public LocalDateTime getDate() { return date; }
        public void setDate(LocalDateTime date) { this.date = date; }
    }

    public static class RegistrationSummary {
        private String currentTermId;
        private int registeredCredits;
        private int courseCount;
        private String registrationStatus;
        private List<Course> courses;

        public String getCurrentTermId() { return currentTermId; }
        public void setCurrentTermId(String currentTermId) { this.currentTermId = currentTermId; }
        public int getRegisteredCredits() { return registeredCredits; }
        public void setRegisteredCredits(int registeredCredits) { this.registeredCredits = registeredCredits; }
        public int getCourseCount() { return courseCount; }
        public void setCourseCount(int courseCount) { this.courseCount = courseCount; }
        public String getRegistrationStatus() { return registrationStatus; }
        public void setRegistrationStatus(String registrationStatus) { this.registrationStatus = registrationStatus; }
        public List<Course> getCourses() { return courses; }
        public void setCourses(List<Course> courses) { this.courses = courses; }
    }
}
