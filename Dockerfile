FROM openjdk:10-jre-slim

VOLUME /tmp
ARG JAR_FILE=build/libs/configuration-service-1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} configuration-service.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/configuration-service.jar"]