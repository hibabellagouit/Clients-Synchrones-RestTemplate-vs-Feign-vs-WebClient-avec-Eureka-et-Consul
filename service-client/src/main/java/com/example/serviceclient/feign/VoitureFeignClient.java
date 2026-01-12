package com.example.serviceclient.feign;

import com.example.serviceclient.model.Voiture;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-voiture", url = "http://localhost:8081")
public interface VoitureFeignClient {

    @GetMapping("/api/cars/byClient/{clientId}")
    Voiture getVoitureByClientId(@PathVariable("clientId") Integer clientId);
}
