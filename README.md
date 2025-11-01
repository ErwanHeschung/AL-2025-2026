# Documentation générée — AL-2025-2026

Ce document regroupe des fichiers markdown prêts à déposer dans le repo GitHub **ErwanHeschung/AL-2025-2026**. Il contient :

* `README.md` — comment lancer / utiliser le projet (Quickstart + prerequis)
* [CONTRIBUTING.md](CONTRIBUTION.md) — règles de contribution
* [ARCHITECTURE.md](ARCHITECTURE.md) — description de l'architecture, justification technique et diagramme logique
* [RISK_MATRIX.md](RISK_MATRIX.md) — matrice d'analyse de risques (probabilité / impact / mitigation)

## Context

Contexte
Solution d’e-santé visant à détecter principalement les insuffisances cardiaques et à aider les personnes âgées à rester à domicile. Le système permet de partager des informations médicales sélectionnées avec les proches ou les professionnels de santé.

Les capteurs sont intégrés dans un bracelet connecté en Bluetooth Low Energy (BLE). 
Un site web permet la visualisation des données selon le profil de l’utilisateur (patient, médecin, aidant et proche).
Des formulaires sont également disponibles sur le site pour enregistrer ou compléter des informations.
Chaque bracelet est associé à un utilisateur via l’interface web pour assurer la traçabilité et la cohérence des données.

## But
Collecte de données depuis un bracelet IoT, ingestion via un IOT gateway, pipeline Kafka, micro-services back-end et interface web.

## Structure 

```
AL-2025-2026/
├─ bracelet/ # code relatif aux devices physiques (firmware ou simulateurs)
├─ iot_gateway/ # passerelle IoT (ingestion, prétraitement)
├─ kafka/ # scripts/configuration Kafka (topics, docker-compose)
├─ micro-services/ # plusieurs services Java (API REST, consumer Kafka)
├─ web-interface/ # front-end TypeScript/HTML/CSS
├─ .gitignore
├─ README.md
 ```

## Prérequis
- Java 25 et Maven
- Node.js 18+ et npm/yarn pour `web-interface`
- Docker & Docker Compose
- Kafka
- (Optionnel) IDE: IntelliJ / VSCode

## Installation rapide (dev)
1. Cloner le repo:
   ```bash
   git clone https://github.com/ErwanHeschung/AL-2025-2026.git
   cd AL-2025-2026
    ```

2. Lancer les dépendances (Kafka, Zookeeper, base si nécessaire)
via docker-compose (exemple générique):

   ```bash
   docker compose -f infra/docker-compose.yml up -d
   # si infra absent: docker compose up -d (ou consultez le dossier kafka)
   ```
3. Construire et lancer les micro-services Java:

    Lancer chaque projet microservices avec Maven via console ou Intellij

   ou avec Docker et les 3 fichiers présents dans le dossier de micro-services :

    * `build-all.sh`
    * `run-all.sh`
    * `stop-all.sh`


4. Lancer la web-interface:

   ```bash
   cd web-interface
   npm install
   npm start
   ```
5. Lancer les composants IoT / simulateurs:

   * `bracelet` `iot_gateway` contiennent respectivement le code qui simule et reçoit les messages du bracelet. Lancer avec Node ou docker.

## Commandes utiles

* `docker compose ps` — vérifier l'état des containers

## TODO

* Avoir un script pour lancer tout le projet
* Flow d'alerte 

