package com.example.finishwithboot.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "date_of_start")
    private String dateOfStart;

    @Column(name = "image", length = 10000)
    private String image;

    @ManyToMany(cascade = {MERGE, REFRESH, DETACH, REMOVE}, fetch = FetchType.LAZY)
    @JoinTable(name = "groups_courses",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student> students;

    @ManyToOne
    private Company company;


    public Group(String groupName, String dateOfStart, String image) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.image = image;
    }

    public void addStudent(Student student) {
        if (students==null) {
            students = new ArrayList<>();
        }
        else {
            students.add(student);
        }
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
    }
}