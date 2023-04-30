package com.cies.cies_project.controller;

import com.cies.cies_project.model.entities.User;
import com.cies.cies_project.model.exception.UserNotFoundException;
import com.cies.cies_project.service.UserService;
import com.cies.cies_project.view.dto.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class
UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody RegistrationRequest request) {
        // requestbody'den yeni bir request aliyor
        // bu requestte de user bilgileri var onlari repoya save ediyor
        return userService.registerUser(request);
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {

        return userService.findById(userId)
                .orElseThrow( () -> new UserNotFoundException("User not found. Please sign up first"));

    }

    // burada bir id vererek bu id'deki user'i, yine verilen bir user objesinin
    // ozellikleriyle update ediyoruz
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long id, @RequestBody User newUser) {
        Optional<User> user = userService.findById(id);
        if ( user.isPresent() ) {
            User foundUser = user.get();
            foundUser.setEmail(newUser.getEmail()); // username = email
            foundUser.setPassword(newUser.getPassword());
            return foundUser;
        }
        return null;
    }



}
