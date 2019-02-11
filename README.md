# GRAPHY

Up the services

    cd src/main/docker
    docker network create insight
    docker-compose.exe -f .\graph-index.yml up -d
    docker-compose.exe -f .\graph-db.yml up -d
    docker-compose.exe -f .\graph-janus.yml up -d

Build

    mvn clean compile package -DskipTests=true jib:dockerBuild
    docker login --username=peploleum
    docker tag graphy peploleum/graphy:latest
    docker push peploleum/graphy:latest
