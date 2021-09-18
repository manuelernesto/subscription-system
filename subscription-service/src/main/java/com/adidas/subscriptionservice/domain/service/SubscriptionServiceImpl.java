package com.adidas.subscriptionservice.domain.service;

import com.adidas.subscriptionservice.domain.exception.DomainException;
import com.adidas.subscriptionservice.domain.repository.SubscriptionRepository;
import com.adidas.subscriptionservice.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 16/09/21 07:32
 */
@Slf4j
@AllArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final KafkaTemplate<String, Subscription> kafkaTemplate;

    private final String KAFKA_TOPIC = "subscription-system-subscribe-successful";

    @Override
    public UUID subscribe(Subscription subscription) {
        var emailExists = subscriptionRepository.findSubscriptionByEmail(subscription.getEmail());

        if (emailExists.isPresent()) {
            throw new DomainException("email " + subscription.getEmail() + " is already subscribed.");
        }

        UUID id = subscriptionRepository.save(subscription).getId();
        log.info("User subscribed: " + id);
        subscribToKafka(subscription);
        return id;
    }

    @Override
    public void unSubscribe(String email) {
        var subscription = subscriptionRepository.findSubscriptionByEmail(email);
        subscriptionRepository.delete(subscription.get());
        log.info("Email unSubscribed: " + email);
    }

    private void subscribToKafka(Subscription subscription) {
        kafkaTemplate.send(KAFKA_TOPIC, subscription);
        log.info("Sent request to Kafka with data:" + subscription.toString());
    }
}
