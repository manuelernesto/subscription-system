package com.adidas.subscriptionservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 16/09/21 07:18
 */

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Subscription implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String first_name;
    @NotBlank
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
//    @NotBlank
    private Date date_of_birth;
    @NotNull
    private boolean consent;
}
