# DockerFile for building a docker image for the application
FROM openjdk:17-ea-24-jdk-oracle

WORKDIR /app

COPY target/health-scheduler-api-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_DBCloud  /app/Wallet_DBCloud
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
