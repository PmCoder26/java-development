#   Note - java springboot images also can be build using commmand as:-
#   command: ./mvnw clean install spring-boot:build-image

FROM maven

WORKDIR /app

COPY ./target/discovery-service-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "discovery-service-0.0.1-SNAPSHOT.jar"]

# command to build the docker image using docker file:  docker build -t discovery-service:v0.0.1 .