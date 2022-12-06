package com.example.finishwithboot.service;

import com.example.finishwithboot.model.Course;

import java.util.List;

public interface CourseService {

    void updateCourse(Long id, Course course);

    void deleteCourse(Long id);

    List<Course> getAllCourses(Long companyId);

    Course getCourseById(Long id);

    void saveCourse(Course course,Long id) ;
}