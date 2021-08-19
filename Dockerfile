# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="" \
    APP_DELAY_START=0

# Add a volume pointing to /tmp
VOLUME /tmp
WORKDIR /home/macbook
RUN adduser -D -s /bin/sh macbook
COPY entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown macbook:macbook entrypoint.sh
USER macbook
# Make port 8080 available to the world outside this container
EXPOSE 9999
COPY ./target/*.jar /home/macbook/sang-uaa-service.jar

# Entrypoint
ENTRYPOINT ["./entrypoint.sh"]
