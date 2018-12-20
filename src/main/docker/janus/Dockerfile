FROM openjdk:8u131-jdk

RUN mkdir /app

#COPY janusgraph-0.2.2-hadoop2.zip /app
RUN cd app && wget https://github.com/JanusGraph/janusgraph/releases/download/v0.2.2/janusgraph-0.2.2-hadoop2.zip
RUN apt-get update && apt-get install -y \
    unzip

RUN cd app && unzip janusgraph-0.2.2-hadoop2.zip && rm janusgraph-0.2.2-hadoop2.zip && cd janusgraph-0.2.2-hadoop2

# ADD conf/gremlin-server/gremlin-server-configuration.yaml /app/janusgraph-0.2.2-hadoop2/conf/gremlin-server/gremlin-server-configuration.yaml
# ADD conf/janusgraph.properties /app/janusgraph-0.2.2-hadoop2/conf/janusgraph.properties

WORKDIR /app/janusgraph-0.2.2-hadoop2

EXPOSE 8182
