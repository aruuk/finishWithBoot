package com.example.finishwithboot.repository;

import com.example.finishwithboot.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query( "select i from Instructor i where i.course.id = :courseId")
    List<Instructor> findAllInstructorByCourseId(Long courseId);
}