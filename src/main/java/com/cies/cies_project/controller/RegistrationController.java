package com.cies.cies_project.controller;


import com.cies.cies_project.service.event.RegistrationCompleteEvent;
import com.cies.cies_project.view.dto.RegistrationRequest;
import com.cies.cies_project.model.entities.VerificationToken;
import com.cies.cies_project.model.repositories.VerificationTokenRepository;
import com.cies.cies_project.model.entities.User;
import com.cies.cies_project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest servletRequest) {
        User user = userService.registerUser(registrationRequest);

        // publish registration event
        publisher.publishEvent(new RegistrationCompleteEvent(user, getApplicationUrl(servletRequest)));
        return "Success! Please, check your email to complete the registration.";

    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        VerificationToken theToken = tokenRepository.findByToken(token);
        if ( theToken.getUser().isEnabled() ) {
            return "The account has already been verified! Please Login";
        }

        // validate the token first
        String verificationResult = userService.validateToken(token);
        if ( verificationResult.equalsIgnoreCase("valid")) {
            return "Email verified successfully. You can login to your account!";
        }

        return "Invalid verification";

    }


    public String getApplicationUrl(final HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }



}
