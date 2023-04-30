package com.cies.cies_project.service;

import com.cies.cies_project.model.entities.User;
import com.cies.cies_project.view.dto.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getUsers();
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    void saveUserVerificationToken(User theUser, String verificationToken);

    String validateToken(String theToken);
}
