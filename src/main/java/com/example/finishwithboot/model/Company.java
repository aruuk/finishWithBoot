package com.example.finishwithboot.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "located_country", nullable = false)
    private String locatedCountry;

    @OneToMany(cascade = CascadeType.ALL, fetch = LAZY, mappedBy ="company")
    private List<Course> courses;

    @Column(name = "count_of_students")
    private Long count = 0L;


    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    public void addCourse(Course newCourse) {
        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(newCourse);
    }

    public void plusStudent(Course course1){
        for (Group group : course1.getGroups()) {
            for (Student student: group.getStudents()) {
                count++;
            }
        }
    }

    public void minusStudent(Course course1){
        for (Group group : course1.getGroups()) {
            for (Student student: group.getStudents()) {
                count--;
            }
        }
    }

    public void plus() {
        count++;
    }

    public void minus() {
        count--;
    }

}
