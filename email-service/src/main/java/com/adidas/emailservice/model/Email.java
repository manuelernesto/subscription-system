package com.adidas.emailservice.model;

import lombok.Data;

import java.util.UUID;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 15/09/21 21:16
 */
@Data
public class Email {
    private UUID id;
    private Status status;
}
