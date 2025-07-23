# Use Maven to build the project
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy only the backend/project folder where pom.xml exists
COPY backend/project/ .

# Build the project, skipping tests (optional)
RUN mvn clean package -DskipTests

# Use a smaller JDK image for running
FROM eclipse-temurin:17-jdk

# Create working directory in the final image
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the JAR
CMD ["java", "-jar", "app.jar"]
