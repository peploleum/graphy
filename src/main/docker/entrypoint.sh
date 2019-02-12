echo "The application is starting: ${GRAPHY_LABEL} with parameters: ${ENDPOINT} ${PORT} ${VERTEX_THRESHOLD}"
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar "${HOME}/app.jar" "$@"