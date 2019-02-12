#!/bin/sh

echo "The application is starting: ${GRAPHY_LABEL}"
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar "${HOME}/app.jar" "$@"
