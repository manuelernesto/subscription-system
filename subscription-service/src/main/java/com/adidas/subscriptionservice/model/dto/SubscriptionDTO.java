package com.adidas.subscriptionservice.model.dto;

import com.adidas.subscriptionservice.model.Gender;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 17/09/21 13:16
 */
@Data
public class SubscriptionDTO {
    private String first_name;
    @NotBlank
    @Email
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date_of_birth;
    @NotNull
    private boolean consent;
    @NotNull
    private UUID newsletter_id;
}
