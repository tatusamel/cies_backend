package com.cies.cies_project.model.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Instructor extends BaseUser {
    private boolean can_modify_questions = false;
}
