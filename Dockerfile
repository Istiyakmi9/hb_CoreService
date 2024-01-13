FROM maven:3.8.1-openjdk-17 AS MAVEN

MAINTAINER BOTTOMHALF

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:17-oracle
WORKDIR /app
EXPOSE 80

COPY --from=MAVEN /build/target/hiringbell_coreservice.jar /app/
COPY src/main/resources /app/resources

ENTRYPOINT ["java", "-jar", "hiringbell_coreservice.jar", "--spring.profiles.active=prod"]