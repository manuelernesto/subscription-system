FROM openjdk:17-jdk-alpine
EXPOSE 8761
COPY target/eureka-server-0.0.1-SNAPSHOT.jar eureka-server-0.0.1.jar
ENTRYPOINT ["java","-jar","/eureka-server-0.0.1.jar"]