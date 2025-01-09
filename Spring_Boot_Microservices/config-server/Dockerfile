FROM maven

WORKDIR /app

COPY .mvn/ ./.mvn
COPY src/ ./src
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run"]