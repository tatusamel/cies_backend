package com.cies.cies_project.service;

import com.cies.cies_project.model.entities.User;
import com.cies.cies_project.model.exception.UserAlreadyExistException;
import com.cies.cies_project.view.dto.RegistrationRequest;
import com.cies.cies_project.model.entities.VerificationToken;
import com.cies.cies_project.model.repositories.VerificationTokenRepository;
import com.cies.cies_project.model.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override

    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.findByEmail(request.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistException(
                    "User with email " + request.getEmail() + " already exist.");
        }
        User newUser = new User();
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setEmail(request.getEmail());
        newUser.setProfilePicture(request.getProfilePicture());

        // when we're saving the password, we only save it encoded
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());

        // we both save this user to the repository (database) and return it
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public void saveUserVerificationToken(User theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if ( token == null ) return "Invalid verification token";

        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();

        if ( token.getTokenExpirationTime().getTime() <= calendar.getTime().getTime()) {
            tokenRepository.delete(token);
            return "Token has already expired";
        }

        // if the token hasn't expired, basically if the time given to the user is still
        // not up, then we can enable the user
        user.setEnabled(true);
        userRepository.save(user); // save it back to the database
        return "Valid";
    }

}
