# Use a lightweight production Java 17 JRE image
FROM eclipse-temurin:17-jre-alpine
# Create a writable directory for Spring Boot's temp files (optional but common)
VOLUME /tmp
# Pass in the JAR during build (default: target/*.jar)
ARG JAR_FILE=target/*.jar
# Copy the jar from the build context to the image
COPY ${JAR_FILE} app.jar
# Provide the default command to run your app
ENTRYPOINT ["java","-jar","/app.jar"]