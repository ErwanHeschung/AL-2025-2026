# Contribution

Pour contribuer directement au projet, veuillez suivre les étapes ci-dessous :

1. **Mettre à jour votre branche locale principale**
   ```bash
   git checkout main
   git pull origin main
   ```

2. **Créer une branche de travail dédiée**  
   Nommez votre branche selon le type de travail effectué :  
   - Fonctionnalité : `feat/ma-fonctionnalite`  
   - Correction de bug : `fix/mon-bug`  
   - Documentation : `docs/ajout-doc`  
   
   ```bash
   git checkout -b feat/ma-fonctionnalite
   ```

3. **Respecter le style de code et les règles du projet**  
   Utilisez les outils de linting et formatting configurés pour le projet :  
   - TypeScript/JavaScript : ESLint + Prettier  
   - Assurez-vous que votre code passe sans erreurs ni warnings avant de pousser.

4. **Ajouter des tests unitaires**  
   Toute logique nouvelle ou modifiée doit être couverte par des tests unitaires.  
   Respectez les conventions de tests existantes.

5. **Commit clair et structuré**  
   Utilisez un message de commit clair, précédé d’un type :  
   ```text
   feat: ajout de la fonctionnalité de connexion
   fix: correction du bug sur le formulaire
   docs: mise à jour du README
   ```
   Veillez à ce que chaque commit corresponde à un changement cohérent.

6. **Pousser votre branche sur le dépôt distant**
   ```bash
   git push origin feat/ma-fonctionnalite
   ```

7. **Ouvrir une Pull Request (PR)**  
   - Indiquez clairement l’objectif de la PR.  
   - Ajoutez une description complète, incluant :  
     - Les changements proposés  
     - Les instructions pour tester  
     - Screenshots / exemples le cas échéant  
   - Référez les tickets ou issues associés.

8. **Revue et intégration**  
   Attendez la revue de vos pairs.  
   Corrigez les éventuels retours.  
   Une fois validée, la PR sera fusionnée dans la branche principale.

---

**Règles rapides :**  
- Travaillez toujours sur une branche dédiée.  
- Garder la branche main stable & clean.  
- Respectez le style et la qualité du code.  
- Écrivez des tests.  
- Communiquez clairement dans vos PR.
