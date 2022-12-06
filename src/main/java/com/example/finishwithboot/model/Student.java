package com.example.finishwithboot.model;

import com.example.finishwithboot.enums.StudyFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "study_format")
    private StudyFormat studyFormat;

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH, PERSIST}, fetch = FetchType.EAGER)
    private Group group;

    public Student(String firstName, String lastName, String phoneNumber,
                   String email, StudyFormat studyFormat, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studyFormat = studyFormat;
        this.group = group;
    }
}
