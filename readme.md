# **Football Management API**

## **1. Introduction**
Cette API permet de gérer les équipes de football, les joueurs et les compétitions. Elle est développée avec **Spring Boot**, **PostgreSQL** et sécurisée avec **Spring Security + JWT**.

---

## **2. Prérequis**
Avant d'exécuter le projet, assurez-vous d'avoir installé :
- **JDK 17**
- **PostgreSQL**
- **Maven**
- **Postman** (optionnel pour tester l'API)

---

## **3. Installation et Configuration**

### **3.1 Cloner le projet**
```bash
git clone https://github.com/votre-repository.git
cd football-management
```

### **3.2 Configuration de la base de données**
Modifiez le fichier **`application.properties`** pour pointer vers votre base PostgreSQL locale :
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nice_football
spring.datasource.username=postgres
spring.datasource.password=1234
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
Assurez-vous que PostgreSQL est en cours d'exécution et que la base `nice_football` existe.

---

### **3.3 Installation des dépendances et démarrage du projet**
```bash
mvn clean install
mvn spring-boot:run
```
L'application sera accessible sur **`http://localhost:8080`**.

---

## **4. Endpoints de l'API**

Tous les endpoints sont **ouverts** aux tests sans authentification.

### **4.1 Authentification**
| Méthode | Endpoint               | Description                       | Authentification |
|---------|------------------------|-----------------------------------|------------------|
| `POST`  | `/api/auth/login`      | Authentifie un utilisateur       | ❌              |

### **4.2 Gestion des équipes**
| Méthode | Endpoint               | Description                       | Authentification |
|---------|------------------------|-----------------------------------|------------------|
| `GET`   | `/api/teams`           | Liste toutes les équipes         | ❌              |
| `POST`  | `/api/teams`           | Crée une nouvelle équipe         | ❌              |
| `PUT`   | `/api/teams/{id}`      | Met à jour une équipe            | ❌              |
| `DELETE`| `/api/teams/{id}`      | Supprime une équipe              | ❌              |

### **4.3 Gestion des joueurs**
| Méthode | Endpoint               | Description                       | Authentification |
|---------|------------------------|-----------------------------------|------------------|
| `GET`   | `/api/players`         | Liste tous les joueurs           | ❌              |
| `POST`  | `/api/players`         | Ajoute un nouveau joueur         | ❌              |
| `PUT`   | `/api/players/{id}`    | Met à jour un joueur             | ❌              |
| `DELETE`| `/api/players/{id}`    | Supprime un joueur               | ❌              |

---

## **5. Sécurité et Authentification**

L'API est **actuellement accessible sans authentification**, mais elle a été conçue pour être sécurisée avec **Spring Security** et **JWT (JSON Web Token)**.

Si vous souhaitez activer la sécurité, vous pouvez **modifier le fichier `SecurityConfig.java`** en remplaçant :
```java
.anyRequest().permitAll()
```
par :
```java
.requestMatchers("/api/auth/**").permitAll()
.anyRequest().authenticated()
```
Cela forcera l'authentification pour tous les endpoints sauf `/api/auth/login`.

---

## **6. Test en ligne via Postman et OpenAPI**
L’API peut être testée directement en ligne via **Postman** ou en accédant aux endpoints depuis un navigateur.

Lien vers l’API déployée :
```url
https://project-football-management-gs3y.onrender.com
```

Lien vers l'interface **OpenAPI (Swagger UI)** pour tester les endpoints en ligne :
```url
https://project-football-management-gs3y.onrender.com/swagger-ui/index.html
```

Les utilisateurs peuvent tester tous les endpoints en ligne sans authentification.

---

## **7. Contribution**
Les contributions sont les bienvenues ! Forkez le projet et soumettez une PR.

---

## **8. Auteurs**
- **Yehoudi Vincent**
