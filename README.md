## Project name
PayDay Demo

## Description
This solution is intended for demonstration purposes of microservices implementation using Spring Boot and Docker technologies.
Solution covers sample use cases of conceptual digital banking backend system. Main functional requirements are customer registration,
customer authentication, customer authorisation, account opening, getting account summary, getting transactions and getting email notifications for account opening events. 

## Architecture
Project architected using Microservices pattern -[https://microservices.io/patterns/index.html](https://microservices.io/patterns/index.html).
API Gateway, Microservices, Service Discovery, Circuit Breaker and Load Balancer are main architectural patterns which have been applied. 
Entry point to the system is the API Gateway. API Gateway is responsible for authentication and authorisation of customers and forwarding 
customer requests to related microservices. Microservices are registered Service Discovery automatically as service starts. 
API Gateway consults Service Discovery Service to get ready microservice endpoints. Endpoints are served to gateway using Load Balancing
strategies. Relational database is used for persistence. JWT support added to Spring Security by implementing new servlet filter, Spring Security Authentication and Authentication Providers. Initial authentication is carried out by providing credentials via HTTP Basic Authorization header. Later authentication and authorisation are provided using JWT token in HTTP Bearer Authentication header.

![Architecture](https://user-images.githubusercontent.com/10387661/73612003-4aad3b80-4601-11ea-913b-ca046969e295.png)

## Technologies

Solution is a Java application. Spring Boot, Spring Security, JWT, Spring Eureka Service Discovery, Hystrix, Ribbon, JPA, Hibernate and PostgreSql
are the main frameworks, modules and applications which are the main building blocks of the system. As a build and package management tool
Maven has been used. Docker is the containerization platform of solution.

## Modules

Services has been created as Maven modular project. PayDay-Demo is the parent project. Eureka, accounts-service, customers-service, transactions-service and notifications-service are the modules of the solution. Each module has its corresponding Dockerfile for docker image creation. docker-compose.yml is used to create services and run them in containers considering all the dependencies and network requirements.
## Installation

1. Maven 

Projects can be built using Maven locally. Locate payday-demo folder. And run ```mvn clean package```

2. Docker

To start services as docker containers run ```docker-compose up --build```

## Usage

After running locally or in a docker environment locate browser to http//:localhost:5555/swagger-ui.html for REST API documentation of the API Gateway. Application configurations including ports can be changed in each modules application.properties file.

![Swagger](https://user-images.githubusercontent.com/10387661/73612367-85fd3980-4604-11ea-8e62-f2c319cd7d67.PNG)

Basic flow:

1. Register in system by providing customer information: 

![Sign Up](https://user-images.githubusercontent.com/10387661/73611404-fbfca300-45fa-11ea-8766-a8a1d8c092cc.PNG)

2. Sign in system by providing customer id created in registration and password using Basic HTTP Authorisation header:

![Sign In](https://user-images.githubusercontent.com/10387661/73611403-fb640c80-45fa-11ea-843c-6f7ad9ad1a07.PNG)

3. Call Account, Transaction and Notification services in any order providing JWT token from Sign In request:

Account Opening:

![Account Opening](https://user-images.githubusercontent.com/10387661/73611401-facb7600-45fa-11ea-88a6-3d4c20469213.PNG)

Account Summary:

![Account Summary](https://user-images.githubusercontent.com/10387661/73611402-facb7600-45fa-11ea-99e1-1f5a8548748d.PNG)

Transaction History:

![Transaction History](https://user-images.githubusercontent.com/10387661/73611406-fc953980-45fa-11ea-886a-5a18366b1c92.PNG)

## To do

1. Unit testing
2. Integration testing
3. Notification service
4. Logging
5. Better exception handling
6. Improving customer and account number generation methods
7. PostgreSql containerization. Currently runnging in Azure
8. Hystrix Circuit Breaker integration
9. Bootstrap sql scripts for dummy data.

