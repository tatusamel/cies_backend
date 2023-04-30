package com.cies.cies_project.model.repositories;

import com.cies.cies_project.model.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
