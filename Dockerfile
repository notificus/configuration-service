FROM openjdk:12-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/configuration-service-1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} configuration-service.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/configuration-service.jar"]