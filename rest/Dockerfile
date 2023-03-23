FROM adoptopenjdk/openjdk11

WORKDIR /opt/app

COPY target/rest-0.0.1-SNAPSHOT.jar /opt/app/rest.jar

ENTRYPOINT ["java","-jar","rest.jar"]