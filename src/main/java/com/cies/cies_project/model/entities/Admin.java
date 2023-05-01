package com.cies.cies_project.model.entities;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Admin extends BaseUser {
}
