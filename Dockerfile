FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/estoque-api.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]