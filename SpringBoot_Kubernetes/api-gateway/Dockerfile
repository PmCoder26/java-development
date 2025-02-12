FROM maven

WORKDIR /app

COPY .mvn/ ./.mvn
COPY src/ ./src
COPY pom.xml mvnw ./

RUN ./mvnw dependency:go-offline

#   executes after 60s.
#   sh -c: Executes the command in a shell.
#   sleep 30: Delays for 30 seconds before running the Spring Boot app.
CMD ["sh", "-c", "sleep 90 && ./mvnw spring-boot:run"]
