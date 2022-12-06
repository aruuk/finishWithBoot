package com.example.finishwithboot.service;

import com.example.finishwithboot.model.Instructor;

import java.util.List;

public interface InstructorService {

    void saveInstructor(Long id, Instructor instructor) ;

    void updateInstructor(Long id, Instructor instructor);

    void deleteInstructor(Long id);

    Instructor getInstructorById(Long id);

    List<Instructor> getAllInstructors(Long id);

    void assignedInstructorToCourse(Long instructorId, Long courseId);

    List<Instructor> getInstructors();

}
