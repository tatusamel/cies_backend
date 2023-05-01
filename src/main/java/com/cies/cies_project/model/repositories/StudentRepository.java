package com.cies.cies_project.model.repositories;

import com.cies.cies_project.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
