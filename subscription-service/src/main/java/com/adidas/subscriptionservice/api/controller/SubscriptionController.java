package com.adidas.subscriptionservice.api.controller;

import com.adidas.subscriptionservice.model.Subscription;
import com.adidas.subscriptionservice.domain.repository.SubscriptionRepository;
import com.adidas.subscriptionservice.domain.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 16/09/21 07:43
 */

@AllArgsConstructor
@RestController
@RequestMapping("/v1/subscribers")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionRepository subscriptionRepository;

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable String email) {
        var subscription = subscriptionRepository.findSubscriptionByEmail(email);
        return subscription.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createNewSubscription(@Valid @RequestBody Subscription subscription) {
        return subscriptionService.subscribe(subscription);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable String email) {
        if (subscriptionRepository.findSubscriptionByEmail(email).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        subscriptionService.unSubscribe(email);
        return ResponseEntity.noContent().build();
    }

}
