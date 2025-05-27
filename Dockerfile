FROM gradle:8.5.0-jdk21 AS builder

WORKDIR /app

COPY gradlew .
COPY gradle/wrapper gradle/wrapper

COPY build.gradle settings.gradle ./
COPY gradle gradle
COPY src src

RUN chmod +x ./gradlew

RUN ./gradlew bootJar --no-daemon

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

ENV API_PORT=8080 \
    API_USER=guest \
    API_PASSWORD=guest

EXPOSE ${API_PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]
