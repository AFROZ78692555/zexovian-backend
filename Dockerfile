# Step 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the application
FROM openjdk:17-jdk-slim
COPY --from=build /target/zexovian-backend-0.0.1-SNAPSHOT.jar zexovian-backend.jar
EXPOSE 14189
ENTRYPOINT ["java","-jar","zexovian-backend.jar"]