package com.example.finishwithboot.service;

import com.example.finishwithboot.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents(Long id);

    void saveStudent(Student student, Long id);

    void updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    Student getStudentById(Long id);

    void assignStudentToGroup(Long studentId, Long groupId);

    List<Student> getStudents();

}
