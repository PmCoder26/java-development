FROM maven

WORKDIR /app

COPY .mvn/ ./.mvn
COPY src/ ./src
COPY pom.xml mvnw ./

RUN ./mvnw dependency:go-offline

#   executes after 30s.
CMD ["sh", "-c", "sleep 30 && ./mvnw spring-boot:run"]