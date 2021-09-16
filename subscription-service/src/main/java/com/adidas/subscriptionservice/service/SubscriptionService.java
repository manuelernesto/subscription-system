package com.adidas.subscriptionservice.service;

import com.adidas.subscriptionservice.model.Subscription;

import java.util.UUID;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 16/09/21 07:32
 */
public interface SubscriptionService {

    UUID subscribe(Subscription subscription);

    void unSubscribe(String email);

}
