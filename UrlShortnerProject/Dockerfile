# STAGE 1: Build the JAR
# We use a Maven image based on Eclipse Temurin (Standard Java)
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
# First, copy just the pom.xml to leverage Docker caching
COPY Url_Shortner/pom.xml .
# Download dependencies
RUN mvn dependency:go-offline
# Then copy the rest of the source code
COPY Url_Shortner/src ./src
# Build the JAR, skipping tests to save time
RUN mvn clean package -DskipTests

# STAGE 2: Create the final tiny image
# We use the JRE (Runtime only) to keep the image small
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copy only the JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]