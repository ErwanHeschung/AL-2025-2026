# Documentation — AL-2025-2026

Ce repository contient :

* `README.md` — comment lancer / utiliser le projet (Quickstart + prerequis)
* [CONTRIBUTING.md](docs/CONTRIBUTION.md) — règles de contribution
* [ARCHITECTURE.md](docs/ARCHITECTURE.md) — description de l'architecture, justification technique et diagramme logique
* [RISK_MATRIX.md](docs/RISK_MATRIX.md) — matrice d'analyse de risques

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
├─ web-interface/ # front-end Angular TypeScript/HTML/CSS
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

### Docker :

Lancez `run-all.sh` à la racine du projet pour lancer tout les composants.

### Manuellement :

1. Lancer les dépendances (Kafka, Zookeeper, KafkaUI)
via docker-compose:

   ```bash
   docker compose -f kafka/docker-compose.yml up -d
   ```
2. Construire et lancer les micro-services Java:

    Lancer chaque projet microservices avec Maven via console ou Intellij

   ou avec Docker et les 3 fichiers présents dans le dossier de micro-services :

    * `build-all.sh`
    * `run-all.sh`
    * `stop-all.sh`


3. Lancer la web-interface:

   ```bash
   cd web-interface
   npm install
   npm start
   ```
4. Lancer les composants IoT / simulateurs:

   * `iot_gateway` `bracelet` contiennent respectivement le code qui reçoit les messages du bracelet et le code qui simule et le code qui simule. Lancer avec Node ou docker.

## Scénario courant

État actuel du PoC :

* Communication : le bracelet et la gateway communiquent via WebSocket pour simuler le BLE.

* Collecte et ingestion : la gateway envoie les données de capteurs vers Kafka (mesures brutes).

* Traitement : les microservices consomment les messages Kafka et les enregistrent dans TimescaleDB.

* Stockage et accès : les données sont historisées dans TimescaleDB et accessibles via l’interface web.

* Formulaires : possibilité de créer et consulter des formulaires depuis l’interface web.

## Commandes utiles

* `docker compose ps` — vérifier l'état des containers

## TODO

* Flow d'alerte 

