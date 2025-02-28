# Utiliser une image Java 17
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR généré dans le conteneur
COPY target/app.jar app.jar

# Exposer le port (change si nécessaire)
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
