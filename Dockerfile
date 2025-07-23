FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only the backend folder where pom.xml exists
COPY backend/ .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
