package com.yourproject.ims.service;

import com.yourproject.ims.dto.DashboardResponse;
import com.yourproject.ims.model.Announcement;
import com.yourproject.ims.model.Grade;
import com.yourproject.ims.model.Registration;
import com.yourproject.ims.model.Term;
import com.yourproject.ims.repository.AnnouncementRepository;
import com.yourproject.ims.repository.GradeRepository;
import com.yourproject.ims.repository.RegistrationRepository;
import com.yourproject.ims.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private TermRepository termRepository;

    public DashboardResponse getDashboardData(String studentId) {
        DashboardResponse response = new DashboardResponse();

        Optional<Term> activeTermOpt = termRepository.findByActiveTrue();
        String currentTermId = activeTermOpt.map(Term::getId).orElse("2025/1");

        List<Announcement> announcements = announcementRepository.findAll();

        List<DashboardResponse.Announcement> dtoAnnouncements = new ArrayList<>();
        for (Announcement dbAnn : announcements) {
            DashboardResponse.Announcement dtoAnn = new DashboardResponse.Announcement();
            dtoAnn.setId(dbAnn.getId());
            dtoAnn.setTitle(dbAnn.getTitle());
            dtoAnn.setContent(dbAnn.getContent());
            dtoAnn.setPostedBy(dbAnn.getPostedBy());
            dtoAnn.setDate(dbAnn.getDate());
            dtoAnnouncements.add(dtoAnn);
        }
        response.setAnnouncements(dtoAnnouncements);

        List<Grade> dbGrades = gradeRepository.findByStudentId(studentId);
        List<DashboardResponse.RecentGrade> recentGrades = new ArrayList<>();
        int passedCourses = 0;

        for (Grade g : dbGrades) {
            DashboardResponse.RecentGrade rg = new DashboardResponse.RecentGrade();
            rg.setCourseCode(g.getCourseCode());
            rg.setCourseName(g.getCourseName());
            rg.setGrade(g.getGrade());
            rg.setStatus(g.getStatus());
            rg.setCredits(g.getCredits());
            recentGrades.add(rg);

            if ("PASS".equalsIgnoreCase(g.getStatus())) {
                passedCourses++;
            }
        }
        response.setRecentGrades(recentGrades);
        response.setTotalPassedCourses(passedCourses);

        Optional<Registration> regOpt = registrationRepository.findByTermIdAndStudentId(currentTermId, studentId);
        DashboardResponse.RegistrationSummary summary = new DashboardResponse.RegistrationSummary();
        summary.setCurrentTermId(currentTermId);

        if (regOpt.isPresent()) {
            Registration reg = regOpt.get();
            summary.setRegistrationStatus(reg.getValidationStatus());
            summary.setCourses(reg.getCourses());
            summary.setCourseCount(reg.getCourses().size());
        } else {
            summary.setRegistrationStatus("NOT_REGISTERED");
            summary.setCourses(new ArrayList<>());
            summary.setCourseCount(0);
        }
        response.setRegistrationSummary(summary);

        response.setGpaData(new DashboardResponse.GpaData());
        response.setBulletin(new DashboardResponse.Bulletin());

        return response;
    }
}
