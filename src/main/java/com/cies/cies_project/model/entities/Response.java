package com.cies.cies_project.model.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity

public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


    @ManyToOne
    @JoinColumn(name = "stident_id")
    private Student student;

    private String answer;

}
