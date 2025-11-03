## Contexte

La solution proposée s’inscrit dans le domaine de l’e-santé avec un focus particulier sur le suivi préventif des patients souffrant d’insuffisance cardiaque. Elle vise à permettre aux personnes âgées de rester à domicile tout en bénéficiant d’un suivi médical fiable et continu sur plusieurs jours.  

Le dispositif collecte en continu les données physiologiques clés telles que la fréquence cardiaque (BPM), la saturation en oxygène (SpO₂) et l’activité physique. Pour le flux de données classique (hors alertes), une moyenne horaire est calculée afin de fournir une vision synthétique et pertinente de l’état du patient. Cette surveillance régulière a pour but principal la prévention, en détectant précocement des signes avant-coureurs d’aggravation et en évitant les décompensations sévères.  

Intégrée dans un écosystème de soins existant, la solution facilite la collaboration entre médecins, infirmiers et proches aidants. L’interface web centralise l’accès aux données, permet la configuration des seuils d’alerte personnalisés et offre un outil de gestion des patients adapté aux contraintes et exigences du milieu médical. La communication reste qualifiée d’orale et directe, la plateforme servant principalement d’outil d’aide à la décision et de suivi.  

Cette approche professionnelle garantit un accompagnement sécurisé, fiable, axé sur la prévention et adapté aux besoins spécifiques des patients atteints d’insuffisance cardiaque.

## Justification des capteurs et de l’affichage

Le choix des capteurs se concentre sur la fréquence cardiaque (BPM), la saturation en oxygène (SpO₂) et l’activité physique, car ces mesures sont essentielles pour monitorer l’état des patients atteints d’insuffisance cardiaque.  

- **Fréquence cardiaque (BPM) :** Indicateur clé du rythme cardiaque, sa surveillance permet de détecter des anomalies comme la tachycardie ou la bradycardie, qui peuvent précéder une décompensation.  
- **Saturation en oxygène (SpO₂) :** Mesure critique pour évaluer la capacité respiratoire et la qualité de l’oxygénation sanguine, essentielle dans ce contexte pathologique.  
- **Activité physique (accéléromètre) :** Permet d’évaluer le niveau d’activité et mobilité du patient, indicateurs indirects de son état général et de sa capacité fonctionnelle.  

L’affichage des données dans l’interface web est conçu pour être synthétique et pertinent, avec une moyenne horaire sur le flux classique afin de limiter le bruit et faciliter la prise de décision médicale. Les alertes critiques sont mises en avant en temps réel afin de garantir une réaction rapide en cas d’urgence.

Cette combinaison capteurs/affichage offre un équilibre entre précision, pertinence et simplicité, adapté aux contraintes d’utilisation par des patients âgés et à la charge de travail du personnel médical.

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
