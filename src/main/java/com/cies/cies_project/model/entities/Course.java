package com.cies.cies_project.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String course_code;
    private String course_name;
    private Integer credit;

    @Enumerated(EnumType.STRING)
    private CourseType course_type;

    private Integer number_of_students;
    private Boolean passed_enroll_limit;

    @ManyToOne
    @JoinColumn
    private Instructor instructor;
}
