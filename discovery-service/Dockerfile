FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY build/libs/discovery-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
