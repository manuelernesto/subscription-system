package com.adidas.subscriptionservice.domain.service;

import com.adidas.subscriptionservice.domain.exception.DomainException;
import com.adidas.subscriptionservice.model.Subscription;
import com.adidas.subscriptionservice.domain.repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 16/09/21 07:32
 */
@AllArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public UUID subscribe(Subscription subscription) {
        var emailExists = subscriptionRepository.findSubscriptionByEmail(subscription.getEmail());

        if (emailExists.isPresent()) {
            throw new DomainException("email " + subscription.getEmail() + " is already subscribed.");
        }

        return subscriptionRepository.save(subscription).getId();
    }

    @Override
    public void unSubscribe(String email) {
        var subscription = subscriptionRepository.findSubscriptionByEmail(email);
        subscriptionRepository.delete(subscription.get());
    }
}
