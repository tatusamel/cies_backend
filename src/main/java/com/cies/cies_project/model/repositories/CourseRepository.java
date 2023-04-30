package com.cies.cies_project.model.repositories;

import com.cies.cies_project.model.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
