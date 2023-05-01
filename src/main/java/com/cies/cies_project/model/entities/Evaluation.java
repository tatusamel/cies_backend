package com.cies.cies_project.model.entities;

import com.cies.cies_project.model.enums.EvaluationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    /*
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;*/

    private Date start_date;
    private Date end_date;

    @Enumerated(EnumType.STRING)
    private EvaluationStatus status;

    private Boolean is_completed;


}
