package com.adidas.emailservice.domain.service;

import com.adidas.emailservice.domain.repository.EmailRepository;
import com.adidas.emailservice.model.Subscription;
import com.adidas.emailservice.model.Email;
import com.adidas.emailservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 18/09/21 01:32
 */
@Slf4j
@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Override
    public Email sendEmail(Subscription subscription) {

        var email = new Email();
        email.setEmail_to(subscription.getEmail());
        email.setSend_date(LocalDateTime.now());

        try {
            //Defining all properties to send the email.
            var message = new SimpleMailMessage();
            message.setFrom("the email from here");
            message.setTo(email.getEmail_to());
            message.setSubject("the subject goes here");
            message.setText("the body goes here");

            //sender.send(message); //Should send email if the configuration is okay

            email.setStatus(Status.SENT);

            log.info(this.getClass().getName(), "Email sended succeseful.");

        } catch (MailException e) {
            email.setStatus(Status.ERROR);
            log.error("Error sending email.", e);
        } finally {
            log.info("Saving email to database.");
            email = emailRepository.save(email);
        }
        return email;
    }
}
