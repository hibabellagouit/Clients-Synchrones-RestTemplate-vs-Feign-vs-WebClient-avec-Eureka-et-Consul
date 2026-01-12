package com.example.serviceclient.service;

import com.example.serviceclient.model.Voiture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateVoitureService {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateVoitureService.class);
    
    // URL du service voiture avec nom logique pour Eureka/Consul
    private static final String SERVICE_VOITURE_URL = "http://service-voiture/api/cars/byClient/";
    
    // URL de test directe (pour les tests initiaux avant configuration discovery)
    private static final String DIRECT_VOITURE_URL = "http://localhost:8081/api/cars/byClient/";
    
    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateVoitureService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Récupère une voiture par clientId en utilisant RestTemplate
     * @param clientId l'ID du client
     * @return la voiture trouvée
     */
    public Voiture getVoitureByClientId(Integer clientId) {
        logger.info("Appel RestTemplate pour clientId: {}", clientId);
        
        // Pour les tests initiaux, utiliser l'URL directe
        String url = DIRECT_VOITURE_URL + clientId;
        
        // Une fois Eureka/Consul configuré, décommenter la ligne suivante et commenter celle ci-dessus
        // String url = SERVICE_VOITURE_URL + clientId;
        
        try {
            long startTime = System.currentTimeMillis();
            Voiture voiture = restTemplate.getForObject(url, Voiture.class);
            long endTime = System.currentTimeMillis();
            
            logger.info("RestTemplate - Temps de réponse: {}ms pour clientId: {}", (endTime - startTime), clientId);
            return voiture;
        } catch (Exception e) {
            logger.error("Erreur lors de l'appel RestTemplate pour clientId: {}", clientId, e);
            throw new RuntimeException("Impossible de récupérer la voiture via RestTemplate", e);
        }
    }
}
