FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY build/libs/config-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]
