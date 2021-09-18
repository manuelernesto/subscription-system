package com.adidas.emailservice.api.controller;

import com.adidas.emailservice.domain.repository.EmailRepository;
import com.adidas.emailservice.domain.service.EmailService;
import com.adidas.emailservice.dto.EmailDTO;
import com.adidas.emailservice.model.Email;
import com.adidas.emailservice.model.Status;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 18/09/21 01:38
 */
@AllArgsConstructor
@RestController
@RequestMapping("/v1/sending-email")
public class EmailController {

    private final EmailService emailService;
    private final EmailRepository emailRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String sendingEmail(@Valid @RequestBody EmailDTO emailDTO) {
        emailService.sendEmail(emailDTO);
        return "Email was sended.";
    }

    @GetMapping
    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    @GetMapping("/{status}")
    public List<Email> getAllEmailsByStatus(@PathVariable String status) {
        return emailRepository.findAllByStatus(Status.valueOf(status.toUpperCase()));
    }
}
