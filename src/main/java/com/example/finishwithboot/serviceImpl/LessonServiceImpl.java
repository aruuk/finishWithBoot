package com.example.finishwithboot.serviceImpl;

import com.example.finishwithboot.model.Course;
import com.example.finishwithboot.model.Lesson;
import com.example.finishwithboot.repository.CourseRepository;
import com.example.finishwithboot.repository.LessonRepository;
import com.example.finishwithboot.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        Course course = courseRepository.findById(courseId).get();
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = lessonRepository.findById(id).get();
        lesson1.setLessonName(lesson.getLessonName());
        lessonRepository.save(lesson1);
    }

    @Override
    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        lesson.setCourse(null);
        lessonRepository.delete(lesson);
    }

    @Override
    public List<Lesson> getLessons(Long courseId) {
        return lessonRepository.getLessonByCourseId(courseId);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).get();
    }
}
