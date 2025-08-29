# Use an official JDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy maven wrapper & pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Package the application (creates a jar in target/)
RUN ./mvnw package -DskipTests

# Run the Spring Boot jar (replace with your jar name if needed)
CMD ["java", "-jar", "target/*.jar"]