MAINTAINER mrmoralesu@gmail.com

#build
#FROM gradle:8.1.1-jdk17 AS build
#
#WORKDIR /src
#
#COPY --chown=gradle:gradle . /src
#
#RUN gradle build -x test --no-daemon

# run
FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

ENV DB_HOST=containers-us-west-199.railway.app DB_PORT=7627 DB_NAME=railway
ENV DB_USER=postgres

ARG PASSWORD_DB
ENV PASSWORD_DB ${PASSWORD_DB}

#COPY --from=build /src/build/libs/*.jar app.jar
COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]