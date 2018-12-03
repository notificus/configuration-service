#################################################################################
# Stage 1
#
FROM gradle:jdk10-slim as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

#################################################################################
# Stage 2
#
FROM openjdk:10-jre-slim
ARG JAR_FILE=build/libs/configuration-service-1.0.jar
COPY --from=builder /home/gradle/src/${JAR_FILE} ./configuration-service.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/configuration-service.jar"]