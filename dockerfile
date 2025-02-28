# Utiliser une image de base OpenJDK
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et les sources
COPY pom.xml .
COPY src ./src

# Télécharger les dépendances et compiler le projet
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Copier le fichier JAR généré
COPY target/*.jar app.jar

# Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Lancer l'application
CMD ["java", "-jar", "app.jar"]
