package com.example.finishwithboot.service;

import com.example.finishwithboot.model.Lesson;

import java.util.List;

public interface LessonService {

    void saveLesson(Long courseId, Lesson lesson) ;

    void updateLesson(Long id, Lesson lesson) ;

    void deleteLesson(Long id);

    List<Lesson> getLessons(Long courseId);

    Lesson getLessonById(Long id);
}
