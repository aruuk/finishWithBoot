package com.example.finishwithboot.repository;

import com.example.finishwithboot.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}