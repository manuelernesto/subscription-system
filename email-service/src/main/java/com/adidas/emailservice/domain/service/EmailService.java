package com.adidas.emailservice.domain.service;

import com.adidas.emailservice.dto.EmailDTO;
import com.adidas.emailservice.model.Email;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 18/09/21 01:31
 */
public interface EmailService {
    Email sendEmail(EmailDTO emailDTO);
}
