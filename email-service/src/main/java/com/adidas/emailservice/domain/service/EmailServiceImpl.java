package com.adidas.emailservice.domain.service;

import com.adidas.emailservice.domain.repository.EmailRepository;
import com.adidas.emailservice.dto.EmailDTO;
import com.adidas.emailservice.model.Email;
import com.adidas.emailservice.model.Status;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 18/09/21 01:32
 */
@AllArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    @Override
    public Email sendEmail(EmailDTO emailDTO) {
        var email = new Email();
        email.setEmail_to(emailDTO.getEmail());
        email.setSend_date(LocalDateTime.now());

        try {
            //Definnit the pro
            var message = new SimpleMailMessage();
            message.setFrom(""); //our email
            message.setTo(email.getEmail_to());
            message.setSubject("New Subscription"); //
            message.setText("");

            //sender.send(message);

            email.setStatus(Status.SENT);
        } catch (MailException e) {
            email.setStatus(Status.ERROR);
        } finally {
            return emailRepository.save(email);
        }
    }
}
