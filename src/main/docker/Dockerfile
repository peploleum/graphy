FROM openjdk:8-jre-alpine

ENV GRAPHY_LABEL="--- GRAPHY ---"
ENV JAVA_OPTS=""

# Add a graphy user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh graphy
WORKDIR /home/graphy

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown graphy:graphy entrypoint.sh
USER graphy

ADD *.jar app.jar

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

