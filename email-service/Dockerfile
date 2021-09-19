FROM openjdk:17-jdk-alpine
EXPOSE 8082
COPY target/email-service-0.0.1-SNAPSHOT.jar email-service-0.0.1.jar
ENTRYPOINT ["java","-jar","/email-service-0.0.1.jar"]