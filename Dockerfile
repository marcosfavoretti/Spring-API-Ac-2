FROM openjdk:17

WORKDIR /ac2

COPY target/*.jar /ac2/

EXPOSE 8585

CMD ["java", "-jar", "/ac2_ca/demo-0.0.1-SNAPSHOT.jar"]
