package com.adidas.emailservice.domain.repository;

import com.adidas.emailservice.model.Email;
import com.adidas.emailservice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 18/09/21 01:29
 */
@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {
    List<Email> findAllByStatus(Status status);
}
