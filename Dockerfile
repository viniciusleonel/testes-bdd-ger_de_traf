FROM maven:3.9.8-eclipse-temurin-21

WORKDIR /opt/app

COPY . .

ENTRYPOINT ["mvn", "clean", "test"]
