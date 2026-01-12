package com.example.serviceclient.service;

import com.example.serviceclient.feign.VoitureFeignClient;
import com.example.serviceclient.model.Voiture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignVoitureService {

    private static final Logger logger = LoggerFactory.getLogger(FeignVoitureService.class);
    
    private final VoitureFeignClient voitureFeignClient;

    @Autowired
    public FeignVoitureService(VoitureFeignClient voitureFeignClient) {
        this.voitureFeignClient = voitureFeignClient;
    }

    /**
     * Récupère une voiture par clientId en utilisant Feign Client
     * @param clientId l'ID du client
     * @return la voiture trouvée
     */
    public Voiture getVoitureByClientId(Integer clientId) {
        logger.info("Appel Feign pour clientId: {}", clientId);
        
        try {
            long startTime = System.currentTimeMillis();
            Voiture voiture = voitureFeignClient.getVoitureByClientId(clientId);
            long endTime = System.currentTimeMillis();
            
            logger.info("Feign - Temps de réponse: {}ms pour clientId: {}", (endTime - startTime), clientId);
            return voiture;
        } catch (Exception e) {
            logger.error("Erreur lors de l'appel Feign pour clientId: {}", clientId, e);
            throw new RuntimeException("Impossible de récupérer la voiture via Feign", e);
        }
    }
}
