FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/public-service-0.0.1-SNAPSHOT.jar public-service-0.0.1.jar
ENTRYPOINT ["java","-jar","/public-service-0.0.1.jar"]