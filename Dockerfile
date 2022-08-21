FROM openjdk:11

LABEL maintainer="president20500@gmail.com"

EXPOSE 8130

ARG JAR_FILE=build/libs/biom-backend-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} /biom-backend.jar

ENTRYPOINT ["java", "-jar","biom-backend.jar"]