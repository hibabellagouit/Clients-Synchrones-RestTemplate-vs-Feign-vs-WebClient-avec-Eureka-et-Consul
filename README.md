# Étude de Cas : Clients Synchrones Spring

Ce projet est une étude de cas comparant trois approches pour effectuer des appels synchrones entre microservices dans un environnement Spring Cloud.

## 📋 Table des Matières
- [Vue d'ensemble](#-vue-densemble)
- [Architecture](#-architecture)
- [Technologies Utilisées](#-technologies-utilisées)
- [Configuration Requise](#-configuration-requise)
- [Installation et Démarrage](#-installation-et-démarrage)
- [Endpoints API](#-endpoints-api)
- [Comparaison des Approches](#-comparaison-des-approches)
- [Structure du Projet](#-structure-du-projet)
- [Journalisation](#-journalisation)
- [Dépannage](#-dépannage)
- [Contributions](#-contributions)
- [Licence](#-licence)

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

## 📝 Journalisation

La journalisation est configurée avec SLF4J et Logback. Les logs sont affichés dans la console et enregistrés dans des fichiers.

## 🔍 Dépannage

### Problèmes courants

1. **Service non enregistré dans Eureka**
   - Vérifiez que le serveur Eureka est en cours d'exécution
   - Vérifiez la configuration de l'URL d'Eureka dans `application.properties`

2. **Erreurs de connexion**
   - Vérifiez que tous les services sont en cours d'exécution
   - Vérifiez les numéros de port dans les fichiers de configuration

## 🤝 Contributions

Les contributions sont les bienvenues ! Voici comment contribuer :

1. Forkez le projet
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/ma-nouvelle-fonctionnalite`)
3. Committez vos changements (`git commit -am 'Ajout d'une nouvelle fonctionnalité'`)
4. Poussez vers la branche (`git push origin feature/ma-nouvelle-fonctionnalite`)
5. Créez une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.
