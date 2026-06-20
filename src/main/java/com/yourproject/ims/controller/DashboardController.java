package com.yourproject.ims.controller;

import com.yourproject.ims.dto.DashboardResponse;
import com.yourproject.ims.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/common/student")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getStudentDashboard(@RequestHeader("X-Student-Id") String studentId) {
        DashboardResponse dashboard = dashboardService.getDashboardData(studentId);
        return ResponseEntity.ok(dashboard);
    }
}
