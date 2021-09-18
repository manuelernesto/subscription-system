package com.adidas.emailservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 15/09/21 21:16
 */
@Data
@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String email_to;
    private LocalDateTime send_date;
}
