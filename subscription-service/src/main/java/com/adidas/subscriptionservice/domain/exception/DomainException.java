package com.adidas.subscriptionservice.domain.exception;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 17/09/21 00:01
 */
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
