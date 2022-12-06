package com.example.finishwithboot.api;

import com.example.finishwithboot.model.Lesson;
import com.example.finishwithboot.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/lessons/{courseId}")
    private String getAllLessons(@PathVariable("courseId") Long courseId, Model model) {
        model.addAttribute("lessons", lessonService.getLessons(courseId));
        model.addAttribute("courseId", courseId);
        return "lesson/lessons";
    }


    @GetMapping("/{courseId}/addLesson")
    private String addLesson(@PathVariable("courseId") Long id, Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("courseId", id);
        return "lesson/addLesson";
    }


    @PostMapping("/{courseId}/saveLesson")
    private String saveLesson(@ModelAttribute("lesson") Lesson lesson,
                              @PathVariable("courseId") Long id) {
        lessonService.saveLesson(id, lesson);
        return "redirect:/lessons/lessons/ " + id;
    }



    @GetMapping("/edit/{id}")
    private String updateLesson(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("courseId", lesson.getCourse().getId());
        return "/lesson/updateLesson";
    }


    @PostMapping("/{courseId}/{lessonId}/update")
    private String saveUpdateLesson(@PathVariable("courseId") Long courseId,
                                    @PathVariable("lessonId") Long lessonId,
                                    @ModelAttribute("lesson") Lesson lesson) {
        lessonService.updateLesson(lessonId, lesson);
        return "redirect:/lessons/lessons/ " + courseId;
    }

    @PostMapping("/{courseId}/{lessonId}/delete")
    private String deleteLesson(@PathVariable("courseId") Long id,
                                @PathVariable("lessonId") Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return "redirect:/lessons/lessons/ " + id;
    }
}
