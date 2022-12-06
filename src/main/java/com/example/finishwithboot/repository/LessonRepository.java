package com.example.finishwithboot.repository;

import com.example.finishwithboot.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> getLessonByCourseId(Long courseId);
}