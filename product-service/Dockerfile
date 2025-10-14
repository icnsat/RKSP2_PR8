FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY build/libs/product-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 9002
ENTRYPOINT ["java", "-jar", "app.jar"]
