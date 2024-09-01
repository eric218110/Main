FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

COPY . .

RUN mv ./src/main/resources/application.qa.properties ./src/main/resources/application.properties

RUN mvn clean install

FROM openjdk:17-slim

EXPOSE 3333

COPY --from=build /target/main-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
