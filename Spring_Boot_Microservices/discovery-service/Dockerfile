#   Note - java springboot images also can be build using commmand as:-
#   command: ./mvnw clean install spring-boot:build-image

FROM maven

WORKDIR /app

#       approach-1
#COPY ./target/discovery-service-0.0.1-SNAPSHOT.jar /app
#
#CMD ["java", "-jar", "discovery-service-0.0.1-SNAPSHOT.jar"]

        # more better approach.
COPY .mvn/ ./.mvn
COPY src/ ./src
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]

# command to build the docker image using docker file:  docker build -t discovery-service:v0.0.1 .