# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Add a volume pointing to /tmp
VOLUME /tmp
# Make port 8080 available to the world outside this container
EXPOSE 9999
COPY ./target/*.jar /usr/app/sang-uaa-service.jar
WORKDIR /usr/app

#enviroment variable default value
ENV SPRING_PROFILE=local
ENTRYPOINT [ "java", "-Dspring.profiles.active=${SPRING_PROFILE}","-jar","sang-uaa-service.jar" ]