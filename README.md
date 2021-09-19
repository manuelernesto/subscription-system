# Subscription System

A system that contains three microservices for use in the subscription applications.

### Design Flow
![alt text](https://github.com/manuelernesto/subscription-system/blob/main/flow.png)

**Microservice**
    - Public Service: Backend for Frontend microservices.
    - Subscription Service: microservices that implements subscription logic, includind persistente of data and email notification to confirm process is completed.
    - Email Service: microservice implementing email notifications.

**Tech Stack**
    - Spring Boot
    - Security : Basic Auth Spring Security
    - Netflix Eureka
    - Swagger
    - Docker
    - PostgreSQL
    - Kafka

**API Examples**
    - Public service: http://localhost:8080/adidas/api/swagger-ui.html
    - Email service:  http://localhost:8082/adidas/api/swagger-ui.html
    - Subscription service:  http://localhost:8081/adidas/api/swagger-ui.html
    -BONUS: download de postman file in the docs folder

### Installation
This application is built using maven
```sh
$ cd email-service
$ mvn spring-boot:run
```
```sh
$ cd subscription-service
$ mvn spring-boot:run
```
```sh
$ cd eureka-server
$ mvn spring-boot:run
```
```sh
$ cd public-service
$ mvn spring-boot:run
```

### Docker
```sh
docker-compose up --build
```

