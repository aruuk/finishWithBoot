package com.example.finishwithboot.serviceImpl;

import com.example.finishwithboot.model.Course;
import com.example.finishwithboot.model.Group;
import com.example.finishwithboot.model.Instructor;
import com.example.finishwithboot.model.Student;
import com.example.finishwithboot.repository.GroupRepository;
import com.example.finishwithboot.repository.StudentRepository;
import com.example.finishwithboot.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override
    public List<Student> getAllStudents(Long id) {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Student student, Long id) {
        Group group = groupRepository.findById(id).get();
        for (Course course : group.getCourses()) {
            course.getCompany().plus();

        }
        for (Course course : group.getCourses()) {
            for (Instructor instructor : course.getInstructors()) {
                instructor.plus();
            }
        }
        validator(student.getPhoneNumber().replace(" ", ""), student.getLastName()
                .replace(" ", ""), student.getFirstName()
                .replace(" ", ""));
        group.addStudent(student);
        student.setGroup(group);
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = getStudentById(id);
        validator(student.getPhoneNumber().replace(" ", ""), student.getLastName()
                .replace(" ", ""), student.getFirstName()
                .replace(" ", ""));
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        studentRepository.save(student1);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).get();
        for (Course course : student.getGroup().getCourses()) {
            course.getCompany().minus();

        }
        for (Course course : student.getGroup().getCourses()) {
            for (Instructor instructor : course.getInstructors()) {
                instructor.minus();
            }
        }
        studentRepository.delete(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @SneakyThrows
    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) {
        Student student = studentRepository.findById(studentId).get();
        Group group = groupRepository.findById(groupId).get();
        if (group.getStudents() != null) {
            for (Student student1 : group.getStudents()) {
                if (student1.getId() == studentId) {
                    throw new IOException("This student already exists!");
                }
            }
        }
        group.addStudent(student);
        student.setGroup(group);
        groupRepository.save(group);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @SneakyThrows
    private void validator(String phoneNumber, String firstName, String lastName) {
        if (firstName.length() > 2 && lastName.length() > 2) {
            for (Character i : firstName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В имени инструктора нельзя вставлять цифры");
                }
            }

            for (Character i : lastName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В фамилию инструктора нельзя вставлять цифры");
                }
            }
        } else {
            throw new IOException("В имени или фамилии инструктора должно быть как минимум 3 буквы");
        }

        if (phoneNumber.length() == 13
                && phoneNumber.charAt(0) == '+'
                && phoneNumber.charAt(1) == '9'
                && phoneNumber.charAt(2) == '9'
                && phoneNumber.charAt(3) == '6') {
            int counter = 0;

            for (Character i : phoneNumber.toCharArray()) {
                if (counter != 0) {
                    if (!Character.isDigit(i)) {
                        throw new IOException("Формат номера не правильный");
                    }
                }
                counter++;
            }
        } else {
            throw new IOException("Формат номера не правильный");
        }
    }
}
