FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY build/libs/order-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 9003
ENTRYPOINT ["java", "-jar", "app.jar"]
