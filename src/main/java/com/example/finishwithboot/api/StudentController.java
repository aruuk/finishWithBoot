package com.example.finishwithboot.api;

import com.example.finishwithboot.model.Course;
import com.example.finishwithboot.model.Group;
import com.example.finishwithboot.model.Student;
import com.example.finishwithboot.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students/{id}")
    private String getAllStudents(@PathVariable Long id, Model model,
                                  @ModelAttribute("course") Course course,
                                  @ModelAttribute("group") Group group) {
        model.addAttribute("students", studentService.getAllStudents(id));
        model.addAttribute("groupId", id);
        return "/student/students";
    }


    @GetMapping("/{id}/addStudent")
    private String addStudent(@PathVariable Long id, Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("groupId", id);
        return "/student/addStudent";
    }

    @PostMapping("/{id}/saveStudent")
    private String saveStudent(@ModelAttribute("student") Student student,
                               @PathVariable Long id) {
        studentService.saveStudent(student, id);
        return "redirect:/students/students/ " + id;
    }

    @GetMapping("/edit/{id}")
    private String updateStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("groupId", student.getGroup().getId());
        return "/student/updateStudent";
    }

    @PostMapping("/{groupId}/{studentId}/updateStudent")
    private String saveUpdateStudent(@PathVariable("studentId") Long studentId,
                                     @PathVariable("groupId") Long id,
                                     @ModelAttribute("student") Student student) {
        studentService.updateStudent(studentId, student);
        return "redirect:/students/students/ " + id;
    }


    @PostMapping("/{id}/{groupId}")
    private String deleteStudent(@PathVariable("id") Long id,
                                 @PathVariable("groupId") Long groupId) {
        studentService.deleteStudent(id);
        return "redirect:/students/students/ " + groupId;
    }

    @PostMapping("/{studentId}/assignStudentToGroup")
    private String assignStudentToGroup(@PathVariable("studentId") Long studentId,
                                        @ModelAttribute("group") Group group) {
        studentService.assignStudentToGroup(studentId, group.getId());
        return "redirect:/students/students/ " + group.getId();
    }
}
