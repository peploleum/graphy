FROM openjdk:8-jre-alpine

ENV GRAPHY_LABEL="--- GRAPHY ---"
ENV JAVA_OPTS=""

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh

ADD graphy*.jar app.jar

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080
