FROM maven:3.9.8-eclipse-temurin-21 AS build

RUN mkdir /opt/app

COPY . /opt/app

WORKDIR /opt/app

RUN mvn clean package

FROM eclipse-temurin:21-jre-alpine

RUN mkdir /opt/app

COPY --from=build  /opt/app/target/testes-automatizados-1.0-SNAPSHOT.jar /opt/app/testes-automatizados-1.0-SNAPSHOT.jar

WORKDIR /opt/app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "testes-automatizados-1.0-SNAPSHOT.jar"]
