# ================================================================================
# Dockerfile: Multi-Stage Build for Spring Boot Auth Service
# Author: Ochwada
# Date: 2025-08-08
# ================================================================================

# ========================
# 1. BUILD STAGE
# ========================
FROM maven:3.9.6-eclipse-temurin-17 AS builder
#FROM eclipse-temurin:17-jdk AS builder

# Set working directory inside build container
WORKDIR /app

# Copy Maven wrapper scripts and config for better layer caching
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./

COPY  pom.xml .

# Pre-fetch dependencies
#RUN ./mvnw dependency:go-offline
RUN mvn -B -q -DskipTests dependency:go-offline

# Copy application source code
COPY src/ ./src

# Build the application (skipping tests for faster build)
#RUN ./mvnw clean package -DskipTests
RUN mvn -B -DskipTests package

# ========================
# 2. RUN STAGE
# ========================
#FROM eclipse-temurin:17-jdk
FROM eclipse-temurin:17-jre

# Set working directory inside runtime container
WORKDIR /app

# Copy the built jar from the builder stage
# COPY --from=builder /app/target/*.jar app.jar
ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} /app/app.jar

# Expose the application port
ENV SERVER_PORT=9080
EXPOSE 9080

# Start the application
# ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "/app/app.jar"]