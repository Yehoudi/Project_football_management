# Football Management API

## Introduction

L'application Football Management API permet de gérer l'équipe de football de l'OGC Nice et leurs joueurs grâce à une API REST.

---

## Fonctionnalités

- Gestion des équipes : création, mise à jour, suppression, récupération.
- Gestion des joueurs : ajout, mise à jour, suppression.
- Pagination et tri des équipes.

---

## Installation

### **Prérequis**

1. **Java 17+**
2. **Maven 3.6+**
3. **PostgreSQL** installé et configuré.

---

### **1. Configuration de la Base de Données**

#### **1.1 Créez la Base PostgreSQL**

1. Ouvrez un terminal ou un outil comme pgAdmin, puis connectez-vous à PostgreSQL :

   ```bash
   psql -U votre_nom_utilisateur
   ```

2. Créez une base de données nommée **nice_football** :

   ```sql
   CREATE DATABASE nice_football;
   ```

3. Créez un utilisateur dédié et attribuez-lui les droits sur cette base :

   ```sql
   CREATE USER votre_nom_utilisateur WITH PASSWORD 'votre_mot_de_passe';
   GRANT ALL PRIVILEGES ON DATABASE nice_football TO votre_nom_utilisateur;
   ```

#### **1.2 Importez la Structure et les Données**

    Le fichier nice_football.sql contient la structure de la base ainsi que des
    données de test. Voici comment l'importer :

1. Téléchargez le fichier SQL fourni avec le projet.

2. Importez-le dans la base nice_football en exécutant cette commande :

   ```bash
   psql -U votre_nom_utilisateur -d nice_football -f /chemin/vers/nice_football.sql
   ```

3. Vérifiez que les tables et les données sont correctement créées :

   a) Listez les tables :

   ```sql
   \dt
   ```

   b) Vérifiez les données des équipes :

   ```sql
   SELECT * FROM team;
   ```

   b) Vérifiez les joueurs associés aux équipes :

   ```sql
    SELECT * FROM player;
   ```

### **1.3 Configurez les Informations de Connexion** :

Une fois la base configurée, mettez à jour le fichier **application.properties** avec vos propres informations :

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/nice_football
    spring.datasource.username=votre_nom_utilisateur
    spring.datasource.password=votre_mot_de_passe
    ```

#### Note : Sans cette configuration, l'application ne pourra pas se connecter à la base.

### 2. Compilez et Lancez le Projet

#### 2.1. Compilez le projet avec Maven :

    ```bash
    mvn clean install
    ```

#### 2.2. Lancez l'application :

    ```bash
    mvn spring-boot:run
    ```

### 3. Endpoints de l'API :

    - GET /api/teams : Récupère la liste paginée des équipes.
    - POST /api/teams : Crée une nouvelle équipe avec des joueurs.
    - PUT /api/teams/{id} : Met à jour une équipe existante.
    - DELETE /api/teams/{id} : Supprime une équipe.

### 4. Architecture

    - Back-end : Java avec Spring Boot.
    - Base de données : PostgreSQL.
    - Gestion des dépendances : Maven.

### Pour plus de détails, y compris une explication approfondie de l'architecture, des choix techniques et des exemples d'utilisation de l'API, veuillez consulter la documentation complète disponible dans le fichier _documentation.pdf_.

## Auteur

Yehoudi VINCENT
