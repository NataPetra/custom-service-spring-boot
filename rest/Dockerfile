FROM adoptopenjdk/openjdk11

WORKDIR /opt/app

COPY target/rest-0.0.1-SNAPSHOT.jar /opt/app/rest-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","rest-0.0.1-SNAPSHOT.jar"]