package com.example.serviceclient.controller;

import com.example.serviceclient.model.Voiture;
import com.example.serviceclient.service.FeignVoitureService;
import com.example.serviceclient.service.RestTemplateVoitureService;
import com.example.serviceclient.service.WebClientVoitureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final RestTemplateVoitureService restTemplateVoitureService;
    private final FeignVoitureService feignVoitureService;
    private final WebClientVoitureService webClientVoitureService;

    @Autowired
    public ClientController(RestTemplateVoitureService restTemplateVoitureService,
                           FeignVoitureService feignVoitureService,
                           WebClientVoitureService webClientVoitureService) {
        this.restTemplateVoitureService = restTemplateVoitureService;
        this.feignVoitureService = feignVoitureService;
        this.webClientVoitureService = webClientVoitureService;
    }

    /**
     * Endpoint pour tester RestTemplate
     */
    @GetMapping("/{id}/car/rest")
    public ResponseEntity<Voiture> getVoitureWithRestTemplate(@PathVariable Integer id) {
        logger.info("Requête reçue pour voiture du client {} avec RestTemplate", id);
        try {
            Voiture voiture = restTemplateVoitureService.getVoitureByClientId(id);
            return ResponseEntity.ok(voiture);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de voiture avec RestTemplate pour client {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Endpoint pour tester Feign Client
     */
    @GetMapping("/{id}/car/feign")
    public ResponseEntity<Voiture> getVoitureWithFeign(@PathVariable Integer id) {
        logger.info("Requête reçue pour voiture du client {} avec Feign", id);
        try {
            Voiture voiture = feignVoitureService.getVoitureByClientId(id);
            return ResponseEntity.ok(voiture);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de voiture avec Feign pour client {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Endpoint pour tester WebClient
     */
    @GetMapping("/{id}/car/webclient")
    public ResponseEntity<Voiture> getVoitureWithWebClient(@PathVariable Integer id) {
        logger.info("Requête reçue pour voiture du client {} avec WebClient", id);
        try {
            Voiture voiture = webClientVoitureService.getVoitureByClientId(id);
            return ResponseEntity.ok(voiture);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de voiture avec WebClient pour client {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Endpoint de santé pour le service client
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service Client is running!");
    }
}
