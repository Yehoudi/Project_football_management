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
Modifiez le fichier **`application.properties`** pour pointer vers votre base PostgreSQL :
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

### **4.1 Authentification**
| Méthode | Endpoint               | Description                       | Authentification |
|---------|------------------------|-----------------------------------|------------------|
| `POST`  | `/api/auth/login`      | Authentifie un utilisateur       | ❌              |

### **4.2 Gestion des équipes**
| Méthode | Endpoint               | Description                       | Authentification |
|---------|------------------------|-----------------------------------|------------------|
| `GET`   | `/api/teams`           | Liste toutes les équipes         | ✅              |
| `POST`  | `/api/teams`           | Crée une nouvelle équipe         | ✅              |
| `PUT`   | `/api/teams/{id}`      | Met à jour une équipe            | ✅              |
| `DELETE`| `/api/teams/{id}`      | Supprime une équipe              | ✅              |

### **4.3 Gestion des joueurs**
| Méthode | Endpoint               | Description                       | Authentification |
|---------|------------------------|-----------------------------------|------------------|
| `GET`   | `/api/players`         | Liste tous les joueurs           | ✅              |
| `POST`  | `/api/players`         | Ajoute un nouveau joueur         | ✅              |
| `PUT`   | `/api/players/{id}`    | Met à jour un joueur             | ✅              |
| `DELETE`| `/api/players/{id}`    | Supprime un joueur               | ✅              |

---

## **5. Sécurité et Authentification**

L'API est sécurisée avec **Spring Security** et utilise un système d'authentification basé sur **JWT (JSON Web Token)**.

### **5.1 Création d'un utilisateur**
Avant de pouvoir s'authentifier, un utilisateur doit être créé dans la base de données avec un mot de passe hashé.

#### **1. Générer un mot de passe hashé**
```java
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "votreMotDePasse";
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
```
Remplacez `"votreMotDePasse"` par le mot de passe souhaité, puis exécutez ce code pour obtenir sa version hashée.

#### **2. Ajouter l'utilisateur dans la base**
```sql
INSERT INTO users (username, password, enabled) VALUES ('yehoudi', '$2a$10$exempleDeHash', true);
INSERT INTO user_roles (user_id, roles) VALUES (1, 'USER');
```
Assurez-vous que l'ID de l'utilisateur correspond bien au `user_id` dans `user_roles`.

---

### **5.2 Authentification via JWT**
Pour récupérer un **token JWT**, effectuez une requête **POST** sur `/api/auth/login` avec le corps suivant :
```json
{
    "username": "yehoudi",
    "password": "votreMotDePasse"
}
```
Si les informations sont correctes, un **token JWT** sera renvoyé dans la réponse.

---

### **5.3 Accès aux endpoints sécurisés**
Une fois le token récupéré, ajoutez-le aux requêtes des endpoints protégés via l’en-tête **Authorization** :
```http
Authorization: Bearer VOTRE_TOKEN
```
Exemple avec **Postman** :
1. Aller dans **Headers**  
2. Ajouter un **Authorization** avec la valeur :  
   ```
   Bearer VOTRE_TOKEN
   ```
3. Envoyer la requête à un endpoint sécurisé (`/api/teams` par exemple)

---

## **6. Notes Importantes**
- Tous les endpoints sont sécurisés sauf `/api/auth/login`.
- Sans un **JWT valide**, les requêtes aux endpoints protégés renverront un **403 Forbidden**.
- Assurez-vous que la base de données contient des utilisateurs et rôles valides avant de tester.

---

## **7. Contribution**
Les contributions sont les bienvenues ! Forkez le projet et soumettez une PR.

---

## **8. Auteurs**
- **Yehoudi Vincent**

