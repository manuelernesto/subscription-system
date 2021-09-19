package com.adidas.emailservice.api.controller;

import com.adidas.emailservice.domain.repository.EmailRepository;
import com.adidas.emailservice.domain.service.EmailService;
import com.adidas.emailservice.model.Email;
import com.adidas.emailservice.model.Status;
import com.adidas.emailservice.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 18/09/21 01:38
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1/emails")
public class EmailController {

    private final EmailService emailService;
    private final EmailRepository emailRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Email sendingEmail(@Valid @RequestBody Subscription subscription) {
        return emailService.sendEmail(subscription);
    }

    @GetMapping
    public List<Email> getAllEmails() {
        log.info("Return all emails");
        return emailRepository.findAll();
    }

    @GetMapping("/{status}")
    public List<Email> getAllEmailsByStatus(@PathVariable String status) {
        log.info("Return all emails filtering by status");
        return emailRepository.findAllByStatus(Status.valueOf(status.toUpperCase()));
    }
}
