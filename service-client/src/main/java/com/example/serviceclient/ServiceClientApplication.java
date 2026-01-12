package com.example.serviceclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceClientApplication.class, args);
    }

    // Bean RestTemplate pour l'injection de dépendances
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Bean WebClient pour l'injection de dépendances
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
