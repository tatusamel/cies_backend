package com.cies.cies_project.model.repositories;

import com.cies.cies_project.model.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
