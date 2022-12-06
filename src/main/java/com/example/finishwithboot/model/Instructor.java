package com.example.finishwithboot.model;

import jakarta.persistence.*;

import lombok.*;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "instructor_gen")
    @SequenceGenerator(name = "instructor_seq",sequenceName = "instructor_seq",allocationSize = 1)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private String specialization;

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH, PERSIST}, fetch = FetchType.EAGER)
    private Course course;

    @Column(name = "count_of_students")
    private Long count = 0L;

    public Instructor(String firstName, String lastName, String phoneNumber, String email, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
    }

    public void plus() {
        count++;
    }

    public void minus() {
        count--;
    }

}
