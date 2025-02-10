FROM maven

WORKDIR /app

COPY .mvn/ ./.mvn
COPY src/ ./src
COPY pom.xml mvnw ./

RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]