package com.cies.cies_project.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Instructor")
public class Instructor extends User {
    private boolean can_modify_questions = false;
}
