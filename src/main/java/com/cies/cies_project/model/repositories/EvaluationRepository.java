package com.cies.cies_project.model.repositories;

import com.cies.cies_project.model.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
}
