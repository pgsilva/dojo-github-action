FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

COPY /build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]