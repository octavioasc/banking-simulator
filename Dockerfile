# syntax=docker/dockerfile:1
FROM maven:3.8.6-amazoncorretto-17
WORKDIR banco
COPY pom.xml ./
RUN mvn dependency:resolve
COPY src ./src
RUN mvn clean package
CMD mvn spring-boot:run