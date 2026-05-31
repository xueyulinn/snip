# syntax=docker/dockerfile:1

# ---------- Build stage ----------
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Copy wrapper and pom first to pre-download dependencies via Docker layer cache
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
# Normalize Windows line endings and make the wrapper executable
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw
RUN ./mvnw -B dependency:go-offline

# Copy sources and package (skip tests to avoid hitting a real database at build time)
COPY src ./src
RUN ./mvnw -B clean package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
