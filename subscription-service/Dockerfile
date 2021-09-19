FROM openjdk:17-jdk-alpine
EXPOSE 8081
COPY target/subscription-service-0.0.1-SNAPSHOT.jar subscription-service-0.0.1.jar
ENTRYPOINT ["java","-jar","/subscription-service-0.0.1.jar"]