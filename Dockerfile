# Use Eclipse Temurin base image for Java 21
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory
#WORKDIR /app

# Copy the JAR file into the container
COPY target/dummy-1.0-SNAPSHOT.jar app.jar

# Expose the port the app runs on (optional, for documentation)
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
