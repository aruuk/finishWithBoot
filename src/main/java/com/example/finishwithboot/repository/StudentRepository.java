package com.example.finishwithboot.repository;

import com.example.finishwithboot.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.group =: groupId")
    List<Student> getStudentByGroupId(Long groupId);
}