package com.cies.cies_project.view.dto;

import lombok.Data;

@Data
public class RegistrationRequest{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String profilePicture;
}
