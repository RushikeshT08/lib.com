# Use official Maven image to build the app
FROM maven:3.9.6-eclipse-temurin-17 as build

WORKDIR /app

# Copy your code and build
COPY . .
RUN mvn clean package -DskipTests

# Use lightweight Java image to run the app
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
