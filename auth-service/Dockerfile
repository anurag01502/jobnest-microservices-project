# Base Image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/*.jar app.jar

# Expose application port
EXPOSE 8081

# Start Spring Boot application
ENTRYPOINT ["java","-jar","app.jar"]