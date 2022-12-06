package com.example.finishwithboot.model;


import lombok.*;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "lesson_gen")
    @SequenceGenerator(name = "lesson_seq",sequenceName = "lesson_seq",allocationSize = 1)
    private Long id;

    @Column(name = "lesson_name")
    private String lessonName;

    @ManyToOne(cascade = {PERSIST,MERGE,DETACH,REFRESH}, fetch = FetchType.EAGER)
    private Course course;

    @OneToMany(cascade = ALL,mappedBy = "lesson")
    private List<Task> tasks;

    public void addTask(Task task) {
        if (tasks==null) {
            tasks=new ArrayList<>();
        } else {
            this.tasks.add(task);
        }
    }
}
