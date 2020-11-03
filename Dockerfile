FROM maven:3.5-jdk-8 AS build
COPY pom.xml ./
COPY src ./src
RUN mvn -DskipTests -P mysql clean package
FROM gcr.io/distroless/java
# Define the 8080 port to be exported
EXPOSE 8080
# Copy the jar application into the container
COPY --from=build target/*.jar ./app.jar
# Specify the command to execute to run the spring boot application
ENTRYPOINT java -jar -Dspring.profiles.active=mysql ./app.jar