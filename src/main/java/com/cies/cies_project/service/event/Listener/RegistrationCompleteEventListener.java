package com.cies.cies_project.service.event.Listener;

import com.cies.cies_project.service.event.RegistrationCompleteEvent;
import com.cies.cies_project.model.entities.User;
import com.cies.cies_project.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Slf4j // for creating log message easily
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener
        implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;

    private final JavaMailSender mailSender;
    private User theUser;
    private String verificationToken;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        // These are what to do after 'event' comes
        // 1. Get the newly registered user
        theUser = event.getUser();

        // 2. Create a verification token for the user
        verificationToken = UUID.randomUUID().toString();

        // 3. Save the verification token for the user to the database (repository)
        userService.saveUserVerificationToken(theUser, verificationToken);

        // 4. Build the verification url to be sent to the user
        // this is the link we will send to the user for verification
        String url = event.getApplicationUrl() + "/register/verifyEmail?token=" + verificationToken;

        // 5.send the email
        try {
            this.sendVerificationEmail(url);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click the link to verify your registration : {}", url);
    }
    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, "+ theUser.getFirstName()+ ", <p>" +
                "<p>Thank you for registering with us," + "" +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" + url + "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("yurthomies@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent);
    }

}
