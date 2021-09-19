package com.adidas.publicservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Manuel Ernesto (manuelernest0)
 * @version 1.0
 * @date 19/09/21 15:29
 */
@Configuration
public class EurekaConfig {
    @LoadBalanced
    @Bean
    public RestTemplate RestTemplate() {
        return new RestTemplate();
    }
}
