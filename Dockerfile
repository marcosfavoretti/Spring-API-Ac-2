FROM openjdk:17

WORKDIR /ac2

COPY target/*.jar /ac2/

EXPOSE 8585

CMD ["java", "-jar", "/ac2/demo-0.0.1-SNAPSHOT.jar"]
