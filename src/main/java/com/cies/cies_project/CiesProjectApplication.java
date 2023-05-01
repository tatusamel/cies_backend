package com.cies.cies_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.cies.cies_project.model.entities"})
public class CiesProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CiesProjectApplication.class, args);
    }

}
