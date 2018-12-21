#!/bin/sh

echo "The application is starting: ${GRAPHY_LABEL}"
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.peploleum.insight.graphy.GraphyApplication"  "$@"
