package com.adidas.emailservice.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 18/09/21 01:18
 */
@Data
public class EmailDTO implements Serializable {
    private String first_name;
    @NotBlank
    @Email
    private String email;
    private String gender;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date_of_birth;
    @NotNull
    private boolean consent;
    private String newsletter_id;
}
