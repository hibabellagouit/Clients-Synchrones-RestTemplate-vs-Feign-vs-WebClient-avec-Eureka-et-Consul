package com.example.serviceclient.service;

import com.example.serviceclient.model.Voiture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientVoitureService {

    private static final Logger logger = LoggerFactory.getLogger(WebClientVoitureService.class);
    
    // URL du service voiture avec nom logique pour Eureka/Consul
    private static final String SERVICE_VOITURE_URL = "http://service-voiture/api/cars/byClient/";
    
    // URL de test directe (pour les tests initiaux avant configuration discovery)
    private static final String DIRECT_VOITURE_URL = "http://localhost:8081/api/cars/byClient/";
    
    private final WebClient webClient;

    @Autowired
    public WebClientVoitureService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    /**
     * Récupère une voiture par clientId en utilisant WebClient (mode synchrone avec block())
     * @param clientId l'ID du client
     * @return la voiture trouvée
     */
    public Voiture getVoitureByClientId(Integer clientId) {
        logger.info("Appel WebClient pour clientId: {}", clientId);
        
        // Pour les tests initiaux, utiliser l'URL directe
        String url = DIRECT_VOITURE_URL + clientId;
        
        // Une fois Eureka/Consul configuré, décommenter la ligne suivante et commenter celle ci-dessus
        // String url = SERVICE_VOITURE_URL + clientId;
        
        try {
            long startTime = System.currentTimeMillis();
            
            Mono<Voiture> voitureMono = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(Voiture.class);
            
            // block() pour rendre l'appel synchrone (pour la comparaison dans ce TP)
            Voiture voiture = voitureMono.block();
            
            long endTime = System.currentTimeMillis();
            
            logger.info("WebClient - Temps de réponse: {}ms pour clientId: {}", (endTime - startTime), clientId);
            return voiture;
        } catch (Exception e) {
            logger.error("Erreur lors de l'appel WebClient pour clientId: {}", clientId, e);
            throw new RuntimeException("Impossible de récupérer la voiture via WebClient", e);
        }
    }
}
