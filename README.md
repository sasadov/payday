## Project name
PayDay Demo

## Description
This solution is intended for demonstration purposes of microservices implementation using Spring Boot and Docker technologies.
Solution covers sample use cases of conceptual digital banking backend system. Main functional requirements are customer registration,
customer authentication, customer authorisation, account opening, getting account summary, getting transactions and getting email notifications]
for account opening events. 

## Architecture
Project architected using Microservices pattern -[https://microservices.io/patterns/index.html](https://microservices.io/patterns/index.html).
API Gateway, Microservices, Service Discovery, Circuit Breaker and Load Balancer are main architectural patterns which have been applied. 
Entry point to the system is the API Gateway. API Gateway is responsible for authentication and authorisation of customers and forwarding 
customer requests to related microservices. Microservices are registered Service Discovery automatically as service starts. 
API Gateway consults Service Discovery Service to get ready microservice endpoints. Endpoints are served to gateway using Load Balancing
strategies. Relational database is used for persistence.

![Architecture](https://user-images.githubusercontent.com/10387661/73612003-4aad3b80-4601-11ea-913b-ca046969e295.png)

## Technologies

Solution is a Java application. Spring Boot, Spring Security, Spring Eureka Service Discovery, Hystrix, Ribbon, JPA, Hibernate and PostgreSql
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

After running locally or docker environment locate browser to http:localhost:5555/swagger-ui.html for REST API documentation of the API Gateway. Application configurations including ports can be changed in each modules application.properties file.


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

