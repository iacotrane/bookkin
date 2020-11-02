FROM maven:3.5-jdk-8 AS build
RUN mvn -f -P mysql pom.xml clean package


FROM gcr.io/distroless/java

# Define the 8080 port to be exported
EXPOSE 8080

# Create a new system group with a new user and switch to it
RUN addgroup -S user && adduser -S local -G user
USER local:user

# Copy the jar application into the container
COPY --from=build target/*.jar /app.jar

# Specify the command to execute to run the spring boot application, and the healtcheck to check the status of the application
ENTRYPOINT java -jar app.jar
HEALTHCHECK --interval=5m --timeout=3s --start-period=30s CMD curl -f http://localhost:8080/actuator/health || exit 1