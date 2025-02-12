FROM maven

WORKDIR /app

COPY .mvn/ ./.mvn
COPY src/ ./src
COPY pom.xml mvnw ./

RUN ./mvnw dependency:go-offline

#   executes after 60s.
CMD ["sh", "-c", "sleep 60 && ./mvnw spring-boot:run"]