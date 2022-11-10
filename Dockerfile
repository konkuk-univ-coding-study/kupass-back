FROM openjdk:11

ARG JAR_FILE=./build/libs/kupass-back-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENV TZ Asia/Seoul

ENTRYPOINT ["java", "-jar", "app.jar"]
