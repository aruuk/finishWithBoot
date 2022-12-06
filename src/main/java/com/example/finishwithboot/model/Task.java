package com.example.finishwithboot.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_name")
    @SequenceGenerator(name = "task_seq",sequenceName = "task_seq",allocationSize = 1)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_text",length = 20000)
    private String taskText;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadline;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.EAGER)
    private Lesson lesson;


}
