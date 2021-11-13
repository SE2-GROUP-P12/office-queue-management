# syntax=docker/dockerfile:1

FROM openjdk:11

WORKDIR /app

COPY ./server/ezqueue/.mvn/ .mvn
COPY ./server/ezqueue/mvnw ./server/ezqueue/pom.xml ./

RUN ./mvnw dependency:go-offline

COPY ./server/ezqueue/src ./src

CMD ["./mvnw", "spring-boot:run"]
