package com.example.finishwithboot.serviceImpl;

import com.example.finishwithboot.model.*;
import com.example.finishwithboot.repository.CourseRepository;
import com.example.finishwithboot.repository.GroupRepository;
import com.example.finishwithboot.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Override
    public void saveGroup(Group group, Long id) {
        Course course = courseRepository.findById(id).get();
        course.addGroup(group);
        group.addCourse(course);
        groupRepository.save(group);
    }

//    @Override
//    public void addGroupByCourseId(Long id, Group group) {
//        Course course = courseRepository.findById(id).get();
//        group.addCourse(course);
//        course.addGroup(group);
//        courseRepository.save(course);
//    }

    @Override
    public Group getById(Long id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found")
        );
    }


    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = groupRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found "));
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setImage(group.getImage());
        groupRepository.save(group1);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = groupRepository.findById(id).get();
        List<Student> students = group.getStudents();
        Long count = students.stream().count();
        for (Course course : group.getCourses()) {
            Long count1 = course.getCompany().getCount();
            count1 -= count;
            course.getCompany().setCount(count1);
            for (Instructor instructor : course.getInstructors()) {
                Long count2 = instructor.getCount();
                count2 -= count;
                instructor.setCount(count2);
            }
        }
        groupRepository.delete(group);
    }



    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getAllGroup(Long id) {
        return courseRepository.findById(id).get().getGroups();
//        Course course = courseRepository.findById(id).get();
//        List<Group> groups = course.getGroups();
//        groups.forEach(System.out::println);
//        return groups;
    }

    @SneakyThrows
    @Override
    public void assignGroupToCourse(Long courseId, Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        Course course = courseRepository.findById(courseId).get();
        if (course.getGroups() != null) {
            for (Group group1 : course.getGroups()) {
                if (group1.getId() == groupId) {
                    throw new IOException("This group already exists!");
                }
            }
        }
        course.addGroup(group);
        group.addCourse(course);
        courseRepository.save(course);
    }
}
