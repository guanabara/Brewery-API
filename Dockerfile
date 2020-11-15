FROM openjdk:11.0.3-jdk-slim-stretch

ENV APPLICATION_NAME brewery-api
ENV APP_JAR brewery-api-0.0.1-SNAPSHOT.jar

RUN mkdir -p /opt/$APPLICATION_NAME
COPY ./target/$APP_JAR /opt/$APPLICATION_NAME/$APP_JAR

EXPOSE 80
ENTRYPOINT java -jar /opt/${APPLICATION_NAME}/${APP_JAR}