# Table des matières

1. [Contexte](#contexte)  
2. [Hypothèses](#hypotheses)  
3. [User Stories](#user-stories)  
4. [Notre solution](#notre-solution)  
5. [Justification](#justification)  
6. [Domaine Driven Development](#domaine-driven-development)  

## Contexte

Dans une population vieillissante, la prévention et le suivi à domicile des pathologies chroniques, notamment l’insuffisance cardiaque, représentent un enjeu majeur de santé publique.  
Les systèmes actuels doivent permettre aux patients de rester chez eux tout en assurant une surveillance fiable, continue et sécurisée de leurs paramètres physiologiques.  
Ce suivi vise à détecter précocement des dégradations de l’état de santé, éviter les hospitalisations évitables et faciliter la coordination entre professionnels de santé, patients et proches aidants.  
L’objectif est d’améliorer la qualité de vie des patients tout en optimisant la prise en charge médicale grâce à un accès synthétique mais pertinent aux données essentielles.

## Hypotheses

### Contexte médical  
* La plateforme s’inscrit dans un écosystème de soins, elle n’est pas un outil autonome : médecins, infirmiers et proches interagissent déjà avec le patient et utilisent cet outil pour étendre la prise en charge.  
* Elle complète les pratiques médicales existantes, sans les remplacer.  
* Le médecin est le principal utilisateur : il prescrit le bracelet, configure les seuils d’alerte, détermine les données pertinentes, et assigne les patients au suivi.  
* La relation entre médecin et infirmier est collaborative : le médecin prend les décisions cliniques et définit les paramètres de surveillance, les infirmiers supervisent les alertes, effectuent les visites à domicile et accompagnent les patients selon les instructions du médecin.  
* Cette relation et communication se font via des canaux traditionnels (téléphone, rencontres physiques), pas via l’application web.  

### Contexte technologique  
* Les patients utilisent un bracelet avec des capteurs embarqués comme source principale de données.  
* La connectivité réseau est supposée généralement disponible au domicile des patients.  
* Une application web constitue le point d’accès central pour tous les acteurs impliqués.  

### Capacités et contraintes des utilisateurs  
* Les patients âgés peuvent avoir des compétences techniques limitées → interfaces, formulaires et dispositifs doivent être simples et accessibles.  
* Le personnel médical dispose de peu de temps → les données doivent être fiables, synthétiques et exploitables, et non brutes.  
* Les proches ont un rôle d’information, non médical → leur accès est restreint en conséquence.  

### Contexte opérationnel  
* Le système est un service de long terme : monitoring, mises à jour et maintenance sont continus.  
* La fiabilité est cruciale : en cas de défaillance, la qualité des soins pourrait être impactée.  
* Les médecins sont les principaux décideurs pour la configuration (seuils d’alerte, pertinence des données, assignation des patients).  

## User Stories

### Médecin  
* En tant que médecin, je peux accéder aux données de mes patients afin de suivre leur état de santé.  
* En tant que médecin, je peux recevoir des alertes si l’état d’un patient devient critique afin de réagir rapidement.  
* En tant que médecin, je peux mettre à jour les seuils d’alerte d’un patient afin qu’ils reflètent son état physiologique.  
* En tant que médecin, je peux créer, modifier ou désactiver un compte patient afin que mes patients utilisent l’application en toute sécurité.  
* En tant que médecin, je peux attribuer un bracelet à un patient afin que ses données de santé soient correctement liées.  
* En tant que médecin, je peux consulter les formulaires remplis par les patients, les infirmiers ou les proches afin de fournir des soins appropriés.  
* En tant que médecin, je peux configurer les données collectées et transmises (par exemple : fréquence cardiaque, activité, taux d'oxygène) afin de ne recevoir que les informations médicalement pertinentes.  

### Infirmier  
* En tant qu’infirmier, je peux accéder aux données des patients qui me sont assignés afin de les assister correctement.  
* En tant qu’infirmier, je peux remplir un formulaire décrivant l’état actuel d’un patient afin que le médecin puisse fournir une assistance spécifique par la suite.  
* En tant qu’infirmier, je peux recevoir des alertes si l’état d’un patient devient critique afin de réagir rapidement.  
* En tant qu’infirmier, je peux mettre à jour des informations non critiques d’un patient (médicaments pris, observations) afin que les dossiers soient à jour.  

### Proche aidant  
* En tant que proche aidant, je peux accéder à des informations limitées sur le patient (par exemple : état général, alertes) afin de rester informé sans voir de données médicales sensibles.  
* En tant que proche aidant, je peux remplir un formulaire simplifié décrivant l’état du patient afin que le médecin soit informé des changements pertinents.  
* En tant que proche aidant, je peux recevoir des alertes si l’état du patient devient critique afin de réagir rapidement.  
* En tant que proche aidant, je peux connecter un nouveau capteur au bracelet afin de surveiller d’autres mesures importantes telles que le poids.  

### Patient  
* En tant que patient, je peux envoyer automatiquement mes données de santé via mon bracelet afin d’être assisté par le personnel médical.  
* En tant que patient, je peux remplir un formulaire décrivant mon état actuel (par exemple : fatigue, douleur) afin que le médecin puisse fournir une assistance adaptée.  
* En tant que patient, je peux visualiser les données collectées et transmises afin d’avoir une transparence sur mes informations.  
* En tant que patient, je peux choisir de partager certaines données non critiques avec mes proches afin de contrôler ma vie privée.  

### Système  
* En tant que système, je peux chiffrer toutes les données transmises et stockées afin de préserver la confidentialité des patients.  
* En tant que système, je peux garantir la disponibilité et la redondance pour que les données soient toujours accessibles.  
* En tant que système, je peux déclencher des alertes dès que les seuils sont dépassés afin que le personnel médical et la famille soient immédiatement informés.  
* En tant que système, je peux enregistrer chaque accès et modification des données afin d’assurer une traçabilité complète.  

### Équipe technique (Nous)  
* En tant qu’équipe de développement et exploitation, je peux surveiller la performance et la disponibilité du système afin d’assurer un service continu.  
* En tant qu’équipe de développement et exploitation, je peux effectuer des mises à jour à distance des bracelets et de la plateforme web afin de maintenir la sécurité et les fonctionnalités à jour.  
* En tant qu’équipe de développement et exploitation, je peux détecter les anomalies ou pannes (ex : dispositif déconnecté, données non envoyées) afin d’alerter l’utilisateur ou le support.  
* En tant qu’équipe de développement et exploitation, je peux fournir aux médecins des options pour configurer précisément quelles données sont collectées pour chaque patient afin que le système s’adapte aux besoins médicaux.  

## Notre solution

Notre solution propose un dispositif connecté centré sur la prévention et le suivi des insuffisants cardiaques à domicile.  
Le système collecte plusieurs données physiologiques clés : fréquence cardiaque (BPM), saturation en oxygène (SpO₂) et niveau d’activité physique.  
Ces mesures sont choisies pour leur pertinence médicale dans la détection précoce de risques d’aggravation.  

Pour le suivi continu, une moyenne horaire des données est calculée afin de limiter le volume d’informations tout en conservant une bonne visibilité sur l’état du patient.  
Les alertes générées lors du dépassement de seuils critiques déclenchent une notification immédiate par courrier électronique à destination des professionnels de santé et des aidants concernés, assurant ainsi une prise en charge rapide.  
L’interface utilisateur est conçue pour être claire, synthétique et accessible, facilitant l’interprétation des données par les professionnels de santé et les aidants.

Le flux classique de données permet un suivi continu et sécurisé des patients, tandis que le système d’alerte garantit la réactivité en cas d’urgence.

## Justification

Cette architecture combine précision des mesures, simplicité d’usage et réactivité dans le pilotage préventif des patients insuffisants cardiaques.  

- La collecte des données physiologiques critiques (BPM, SpO₂, activité) est adaptée à la surveillance spécifique de cette pathologie.  
- La moyenne horaire favorise la clarté des données transmises et limite la surcharge informationnelle pour les équipes médicales.  
- La notification par mail des alertes critiques assure un relais efficace vers les médecins et aidants, améliorant la sécurité du patient et la rapidité d’intervention.  
- L’interface utilisateur intuitive garantit un accès facile aux informations, y compris pour les aidants peu familiers avec les outils numériques.

L’affichage des données dans l’interface web est conçu pour être synthétique et pertinent, avec une moyenne horaire sur le flux classique afin de limiter le bruit et faciliter la prise de décision médicale. Les alertes critiques sont mises en avant en temps réel afin de garantir une réaction rapide en cas d’urgence.  
Cette combinaison capteurs/affichage offre un équilibre entre précision, pertinence et simplicité, adapté aux contraintes d’utilisation par des patients âgés et à la charge de travail du personnel médical.

## Domaine Driven Development

### Core Domain (Domaine principal)  
C’est le coeur métier différenciateur qui apporte de la valeur clinique essentielle :  
- **Gestion des alertes médicales et détection d’anomalies**  
  - Logique de détection des seuils critiques sur les données physiologiques (BPM, SpO₂, activité).  
  - Notification en temps réel (emails, alertes web) aux soignants et proches.  
  - Traitement et historisation des alertes pour suivi et audit médical.  
- **Modélisation du patient et suivi personnalisé**  
  - Agrégation des données patients, paramétrage individuel des seuils, gestion des profils.  
  - Formulaires médicaux liés aux suivis cliniques et décisions médicales.

---

### Supporting Domain (Domaine de support)  
Domaines nécessaires pour soutenir le Core Domain tout en apportant une expertise fonctionnelle spécifique :  
- **Collecte et traitement des données IoT**  
  - Passerelle IoT, communication sécurisée avec le bracelet.  
  - Ingestion et normalisation des données physiologiques en temps réel.  
  - Stockage performant de séries temporelles (TimescaleDB).  
- **Gestion des utilisateurs et authentification**  
  - Contrôle des accès, gestion des rôles (médecin, infirmier, patient, proche).  
  - Administration sécurisée des comptes et permissions.  

---

### Generic Domain (Domaine générique)  
Fonctionnalités transverses et génériques, réutilisables dans différents contextes métiers :  
- **Infrastructure technique**  
  - Orchestration Kubernetes, déploiement continu, surveillance (Prometheus, Grafana).  
  - Messagerie asynchrone (Kafka), base de données relationnelle (PostgreSQL).  
- **Web Interface et API Gateway**  
  - Interface utilisateur générique pour consultation et configuration.  
  - Gateway centralisant l’authentification et le routage entre services.  
- **Logging, monitoring et sécurité**  
  - Gestion des logs, traçabilité, chiffrement TLS, rotation des clés, sauvegardes.

