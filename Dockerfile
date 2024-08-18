# Build stage
FROM eclipse-temurin:20-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon

# Runtime stage
FROM eclipse-temurin:20-jre-alpine
ARG JAR_FILE=build/libs/*.jar
COPY --from=builder /app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
