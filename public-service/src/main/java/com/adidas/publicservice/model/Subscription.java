package com.adidas.publicservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription {
    private String first_name;
    @NotBlank
    @Email
    private String email;
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
    @NotNull
    private Date date_of_birth;
    @NotNull
    private boolean consent;
    @NotNull
    private UUID newsletter_id;
}
