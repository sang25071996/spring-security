#!/bin/sh
echo "The application will start in ${APP_DELAY_START}s..." && sleep ${APP_DELAY_START}
exec java ${JAVA_OPTS} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -jar "${HOME}/sang-uaa-service.jar" "$@"
