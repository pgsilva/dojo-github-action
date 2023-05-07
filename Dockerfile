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

ENV DB_HOST=postgres DB_PORT=5432 DB_NAME=dojodb
ENV DB_USER=mrmorales DB_PASSWORD=pass

#COPY --from=build /src/build/libs/*.jar app.jar
COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]