# Analyse de Risques

| Risques | Causes                                                                 | Conséquences | Barrières préventives | Barrières de récupération | Facteur d’escalation | Facteur d’escalation contrôlé |
|---|------------------------------------------------------------------------|---|---|---|---|---|
| Données manquantes | Perte de connexion bluetooth,<br/>Bracelet déchargé                    | Données manquantes dans l'historique du patient<br/>Impact sur l'alerting | copie locale des données<br/>Monitoring et alertes Prometheus | Relecture Kafka (offset)<br/>Consommation du buffer local<br/>Retry automatique | Absence de tests de couverture<br/>Mauvaise config réseau | monitoring actif |
| Impossible d'accéder aux données du patient | Panne du serveur, maintenance<br/>Défaillance de l'authentification    | Interruption temporaire du suivi patient en temps réel<br/>Perte de confiance utilisateur | Load balancing + réplication des microservices<br/>Cluster Kafka multi-nœuds et réplication de partitions | Redémarrage automatique, backup des données<br/>Failover automatique (cluster actif/passif) | Mauvaise configuration du serveur | |
| Surcharge du système | Pic de trafic, mauvaise configuration<br/>Panne d'un consommateur kafka | Perte de données<br/>Perte de confiance vis à vis du système | Partitionnement des files de messages (traitement par plusieurs consommateurs)<br/>Séparation des Topics (Kafka) | Alerte de surcharge (Prometheus)<br/>Auto-réparation (Kubernetes) | Manque de capacité totale comparé au nombre de messages | Stress tests réguliers |
| Retard des données affichées | Réseau instable, surcharge du backend, bug                             | Données affichées erronées et pouvant être mal interprétées | Optimisation du code et queue agissant de manière asynchrone | | Monitoring inactif | Supervision active avec alertes Prometheus |
| Fuite de données | Interception des messages| Perte de confiance des utilisateurs | Chiffrement des données de bout en bout | Traçabilité des accès au compte du patient | Certificats expirés | sensibilisation utilisateur |

# Ratio Importance/Probabilité

| | **Insignifiant** | **Mineur** | **Modéré**      | **Majeur**        | **Catastrophique**                           |
|---|---|---|-----------------|-------------------|----------------------------------------------|
| **Certain** | | |                 |                   |                                              |
| **Fréquent** | | | Données manquantes | Données retardées |                                              |
| **Occasionnel** | | |                 | Surcharge du système | Impossibilité d'accéder aux données du patients |
| **Rare** | | |                 |                   | Fuite de données                             |
| **Exceptionnel** | | |                 |                   |                                              |