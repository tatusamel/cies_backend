package com.cies.cies_project.model.entities;

import com.cies.cies_project.model.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;


@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    private String questionText;
    private Boolean isMandatory;

    @ManyToOne
    @JoinColumn
    private Evaluation evaluation;


}
