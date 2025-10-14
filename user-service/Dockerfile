FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY build/libs/user-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "app.jar"]
