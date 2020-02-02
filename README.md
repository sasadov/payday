## Project name
PayDay Demo

## Description
This solution is intended for demonstration purposes of microservices implementation using Spring Boot and Docker technologies.
Solution covers sample use cases of conceptual digital banking backend system. Main functional requirements are customer registration,
customer authentication, customer authorisation, account opening, getting account summary, getting transactions and getting email notifications]
for account opening events. 

## Table of Contents
1. ### Architecture
Project architected using Microservices pattern -[https://microservices.io/patterns/index.html](https://microservices.io/patterns/index.html).
API Gateway, Microservices, Service Discovery, Circuit Breaker and Load Balancer are main architectural patterns which have been applied. 
Entry point to the system is the API Gateway. API Gateway is responsible for authentication and authorisation of customers and forwarding 
customer requests to related microservices. Microservices are registered Service Discovery automatically as service starts. 
API Gateway consults Service Discovery Service to get ready microservice endpoints. Endpoints are served to gateway using Load Balancing
strategies. Relational Database is used for persistence.

2. ### Technologies

3. ### Modules

4. ### To do

5. ### Installation

6. ### Usage
