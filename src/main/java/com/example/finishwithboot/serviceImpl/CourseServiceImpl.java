package com.example.finishwithboot.serviceImpl;

import com.example.finishwithboot.model.*;
import com.example.finishwithboot.repository.CompanyRepository;
import com.example.finishwithboot.repository.CourseRepository;
import com.example.finishwithboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<Course> getAllCourses(Long id) {
        return courseRepository.findAll();
    }

    @Override
    public void saveCourse(Course course, Long id) {
        validator(course.getCourseName(), course.getDescription(), course.getDuration());
        Company company = companyRepository.findById(id).get();
        company.addCourse(course);
        course.setCompany(company);
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Long id, Course course){
        validator(course.getCourseName(), course.getDescription(), course.getDuration());
        Course course1 = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found course"));
        course1.setCourseName(course.getCourseName());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        course1.setDateOfStart(course.getDateOfStart());
        courseRepository.save(course1);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        for (Group g: course.getGroups()) {
            g.getCourses().remove(course);
        }
        course.setCompany(null);
        courseRepository.delete(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).get();
    }


    @SneakyThrows
    private void validator(String courseName, String description, int duration) {
        if (courseName.length()>3 && description.length()>5 && description.length()<15 && duration>0 && duration<24) {
            for (Character i : courseName.toCharArray()) {
                if (!Character.isLetter(i)) {
                    throw new IOException("no nums course name");
                }
            }
        }
    }
}
