FROM maven:3.5-jdk-8 AS build
COPY pom.xml ./
COPY src ./src
RUN mvn -DskipTests -P mysql clean package
FROM gcr.io/distroless/java
# Define the 8080 port to be exported
EXPOSE 8080
# Copy the jar application into the container
COPY --from=build target/*.jar ./app.jar
# Specify the command to execute to run the spring boot application, and the healtcheck to check the status of the application
ENTRYPOINT java -jar ./app.jar
HEALTHCHECK --interval=5m --timeout=3s --start-period=30s CMD curl -f http://localhost:8080/actuator/health || exit 1