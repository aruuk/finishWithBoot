package com.example.finishwithboot.serviceImpl;

import com.example.finishwithboot.model.Course;
import com.example.finishwithboot.model.Group;
import com.example.finishwithboot.model.Instructor;
import com.example.finishwithboot.model.Student;
import com.example.finishwithboot.repository.CourseRepository;
import com.example.finishwithboot.repository.InstructorRepository;
import com.example.finishwithboot.service.InstructorService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @SneakyThrows
    @Override
    public void saveInstructor(Long id, Instructor instructor) {
        Course course = courseRepository.findById(id).get();
        List<Instructor> instructors = instructorRepository.findAll();
        for (Instructor i : instructors) {
            if (i.getEmail().equals(instructor.getEmail())){
                throw new IOException("Instructor with email already exists!");
            }
        }
        validator(instructor.getPhoneNumber().replace(" ", ""), instructor.getLastName().replace(" ", ""),
                instructor.getFirstName().replace(" ", ""));
        course.addInstructor(instructor);
        instructor.setCourse(course);
        instructorRepository.save(instructor);
    }

    @SneakyThrows
    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        validator(instructor.getPhoneNumber().replace(" ", ""), instructor.getLastName().replace(" ", ""),
                instructor.getFirstName().replace(" ", ""));
        Instructor instructor1 = instructorRepository.findById(id).get();
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructorRepository.save(instructor1);
    }

    @Override
    public void deleteInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).get();
        instructorRepository.delete(instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).get();
    }

    @Override
    public List<Instructor> getAllInstructors(Long id) {
        return instructorRepository.findAllInstructorByCourseId(id);
    }

    @SneakyThrows
    @Override
    public void assignedInstructorToCourse(Long instructorId, Long courseId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        Course course = courseRepository.findById(courseId).get();
        if (course.getInstructors() != null) {
            for (Instructor instructor1 : course.getInstructors()) {
                if (instructor1.getId() == instructorId) {
                    throw  new IOException("This instructor already exists!");
                }
            }
        }
        Long count = 0L;
        for (Group group : course.getGroups()) {
            for (Student student : group.getStudents()) {
                count++;
            }
        }
        instructor.setCount(count);
        course.addInstructor(instructor);
        instructor.setCourse(course);
        courseRepository.save(course);
        instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> getInstructors() {
        return instructorRepository.findAll();
    }

    private void validator(String phone, String firstName, String lastName) throws IOException {
        if (firstName.length()>2 && lastName.length()>2) {
            for (Character i : firstName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("no nums in instructor name");
                }
            }

            for (Character i : lastName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("no nums in instructor last name");
                }
            }
        } else {
            throw new IOException("need more than 3 letters");
        }

        if (phone.length()==13
                && phone.charAt(0) == '+'
                && phone.charAt(1) == '9'
                && phone.charAt(2) == '9'
                && phone.charAt(3) == '6'){
            int counter = 0;

            for (Character i : phone.toCharArray()) {
                if (counter!=0){
                    if (!Character.isDigit(i)) {
                        throw new IOException("The number format is not correct");
                    }
                }
                counter++;
            }
        }else {
            throw new IOException("The number format is not correct");
        }
    }

}
