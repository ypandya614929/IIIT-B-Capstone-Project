# 1
FROM maven:3.8.2-jdk-11 as build
MAINTAINER ypandya614929
WORKDIR /opt/app
COPY src /opt/app/src
COPY pom.xml /opt/app
RUN mvn clean install spring-boot:repackage

# 2
FROM openjdk:14-jdk-alpine
MAINTAINER ypandya614929
WORKDIR /opt/app
COPY --from=build /opt/app/target/doctor-service.jar /opt/app/doctor-service.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/opt/app/doctor-service.jar"]