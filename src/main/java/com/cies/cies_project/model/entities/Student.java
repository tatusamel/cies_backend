package com.cies.cies_project.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "Student")
public class Student extends User {
}
