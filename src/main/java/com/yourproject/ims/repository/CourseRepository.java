package com.yourproject.ims.repository;

import com.yourproject.ims.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Find all courses available for a specific term
    List<Course> findByTermId(String termId);
}