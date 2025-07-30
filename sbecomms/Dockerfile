FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/sbecomms-1.0-SNAPSHOT.war

COPY ${JAR_FILE} app.war

ENTRYPOINT ["java","-jar","/app.war"]
