package com.example.finishwithboot.model;


import jakarta.persistence.*;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    private Integer duration;

    @Column(name = "description")
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfStart;

    @ManyToOne(cascade = {CascadeType.MERGE, DETACH, REFRESH, PERSIST}, fetch = FetchType.LAZY)
    private  Company company;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "courses")
//    @JoinTable(name = "group_course",joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;

    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Instructor> instructors;

    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Lesson> lessons;



    public Course(String courseName, int duration, String description, LocalDate dateOfStart) {
        this.courseName = courseName;
        this.duration = duration;
        this.description = description;
        this.dateOfStart = dateOfStart;
    }


    public void addGroup(Group group) {
        if (groups==null){
            groups=new ArrayList<>();

        }
        groups.add(group);
    }

    public void addLesson(Lesson lesson) {
        if (lessons==null) {
            lessons=new ArrayList<>();
        }
        else {
            this.lessons.add(lesson);
        }
    }

    public void addInstructor(Instructor instructor) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        } else {
            this.instructors.add(instructor);
        }
    }

}
