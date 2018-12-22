echo "The application is starting: ${GRAPHY_LABEL} with parameters: ${ENDPOINT} ${PORT} ${VERTEX_THRESHOLD}"
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.peploleum.insight.graphy.GraphyApplication"  "$@"
