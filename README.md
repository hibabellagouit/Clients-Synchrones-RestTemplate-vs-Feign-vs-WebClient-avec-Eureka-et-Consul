# Étude de Cas : Clients Synchrones Spring

Ce projet est une étude de cas comparant trois approches pour effectuer des appels synchrones entre microservices dans un environnement Spring Cloud.


## 🌟 Vue d'ensemble

Ce projet démontre trois méthodes pour effectuer des appels HTTP synchrones entre microservices :

1. **RestTemplate** - L'approche traditionnelle de Spring
2. **Feign** - Client HTTP déclaratif
3. **WebClient** - Approche non-bloquante et réactive

## 🏗️ Architecture

Le projet est composé de trois services principaux :

1. **Eureka Server** : Service de découverte pour les microservices
2. **Service Client** : Service consommateur implémentant les trois approches
3. **Service Voiture** : Service fournisseur exposant des API REST

## 🛠️ Technologies Utilisées

- **Langage** : Java 8+
- **Framework** : Spring Boot 2.x, Spring Cloud
- **Service Discovery** : Eureka, Consul
- **Build** : Maven
- **Journalisation** : SLF4J avec Logback

## 📋 Configuration Requise

- Java 8 ou supérieur
- Maven 3.6.0 ou supérieur
- Accès à un serveur Eureka en cours d'exécution
- (Optionnel) Consul pour la découverte de services avancée

## 🚀 Installation et Démarrage

1. **Démarrer Eureka Server** :
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```

2. **Démarrer le Service Voiture** :
   ```bash
   cd ../service-voiture
   mvn spring-boot:run
   ```

3. **Démarrer le Service Client** :
   ```bash
   cd ../service-client
   mvn spring-boot:run
   ```

## 🌐 Endpoints API

### Service Client

#### RestTemplate
- **GET** `/api/clients/{id}/car/rest` - Récupère une voiture avec RestTemplate

#### Feign Client
- **GET** `/api/clients/{id}/car/feign` - Récupère une voiture avec Feign

#### WebClient
- **GET** `/api/clients/{id}/car/webclient` - Récupère une voiture avec WebClient

#### Santé
- **GET** `/api/clients/health` - Vérifie l'état du service

### Service Voiture
- **GET** `/api/voitures/client/{clientId}` - Récupère la voiture d'un client
- **POST** `/api/voitures` - Crée une nouvelle voiture
- **GET** `/api/voitures/health` - Vérifie l'état du service

## 📊 Comparaison des Approches

| Caractéristique       | RestTemplate | Feign | WebClient |
|------------------------|--------------|-------|-----------|
| Type d'appel           | Synchrone    | Synchrone | Asynchrone/Réactif |
| Complexité             | Moyenne      | Faible | Élevée    |
| Performance            | Moyenne      | Moyenne | Élevée    |
| Intégration Eureka    | Manuelle     | Native | Manuelle  |
| Style de programmation | Impératif    | Déclaratif | Réactif  |
| Support des timeouts   | Oui          | Oui   | Oui       |

## 📂 Structure du Projet

```
.
├── eureka-server/         # Serveur de découverte Eureka
├── service-client/        # Service consommateur
│   ├── src/
│   └── pom.xml
├── service-voiture/       # Service fournisseur
│   ├── src/
│   └── pom.xml
└── README.md
```


