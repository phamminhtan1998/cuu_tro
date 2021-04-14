FROM adoptopenjdk/openjdk11-openj9 as builder
WORKDIR /application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
COPY . .
ENTRYPOINT ["java", "-jar", "application.jar"]
