package com.cies.cies_project.view.dto;


public record RegistrationRequest(String firstName,
                                  String lastName,
                                  String email,
                                  String password,
                                  String role) {


}
