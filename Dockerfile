# Etapa 1: Construção (build) da aplicação
FROM maven:3.9.6-eclipse-temurin-21 as builder

WORKDIR /app

COPY . .
RUN mvn clean package

# Etapa 2: Imagem final com apenas o JAR
FROM eclipse-temurin:21-jre

WORKDIR /app

# Remove the unnecessary RUN ls -la

# Correct path
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]