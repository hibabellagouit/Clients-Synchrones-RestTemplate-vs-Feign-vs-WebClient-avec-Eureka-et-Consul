package com.example.servicevoiture.controller;

import com.example.servicevoiture.model.Voiture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class VoitureController {

    private static final Logger logger = LoggerFactory.getLogger(VoitureController.class);
    
    // Base de données en mémoire pour les tests
    private final List<Voiture> voitures = Arrays.asList(
        new Voiture(1L, "Toyota", "Yaris", 1),
        new Voiture(2L, "Renault", "Clio", 2),
        new Voiture(3L, "Peugeot", "208", 1),
        new Voiture(4L, "Volkswagen", "Golf", 3),
        new Voiture(5L, "BMW", "Série 1", 2),
        new Voiture(6L, "Mercedes", "Classe A", 4),
        new Voiture(7L, "Audi", "A3", 1),
        new Voiture(8L, "Ford", "Focus", 3),
        new Voiture(9L, "Citroën", "C3", 2),
        new Voiture(10L, "Hyundai", "i20", 1)
    );

    @GetMapping("/byClient/{clientId}")
    public Voiture getVoitureByClientId(@PathVariable Integer clientId) {
        logger.info("Recherche de voiture pour le clientId: {}", clientId);
        
        // Simulation d'un temps de traitement pour rendre les différences observables
        try {
            Thread.sleep(20); // 20ms de délai artificiel
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Délai interrompu", e);
        }
        
        Optional<Voiture> voiture = voitures.stream()
                .filter(v -> v.getClientId().equals(clientId))
                .findFirst();
        
        if (voiture.isPresent()) {
            logger.info("Voiture trouvée: {}", voiture.get());
            return voiture.get();
        } else {
            logger.warn("Aucune voiture trouvée pour le clientId: {}", clientId);
            // Retourner une voiture par défaut pour éviter les erreurs pendant les tests
            return new Voiture(0L, "Inconnue", "Inconnue", clientId);
        }
    }
    
    @GetMapping("/all")
    public List<Voiture> getAllVoitures() {
        logger.info("Récupération de toutes les voitures");
        return voitures;
    }
    
    @GetMapping("/health")
    public String health() {
        return "Service Voiture is running!";
    }
}
