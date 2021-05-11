# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Add a volume pointing to /tmp
VOLUME /tmp
# Make port 8080 available to the world outside this container
EXPOSE 9999
COPY ./target/*.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT [ "java", "-jar","sang-uaa-service-0.0.1-SNAPSHOT.jar" ]