call mvn package -DskipTests verify jib:dockerBuild
call docker tag graphy peploleum/graphy:1.0.0
call docker push peploleum/graphy:1.0.0
