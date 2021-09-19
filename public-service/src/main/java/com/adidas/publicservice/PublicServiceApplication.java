package com.adidas.publicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class PublicServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicServiceApplication.class, args);
    }

}
