# Microservices Order Management System

## 📌 Project Overview
This project is a scalable and secure microservices-based Order Management System developed using Java 8 and Spring Boot.  
It is designed following real-world enterprise architecture patterns and demonstrates how independent microservices communicate, scale, and handle failures in a distributed environment.

The system is built to simulate an e-commerce backend where users can place orders, products are managed independently, and notifications are handled asynchronously.

## 🎯 Key Objectives
- Build loosely coupled and independently deployable microservices
- Implement secure REST APIs using Spring Security and JWT
- Enable service discovery and centralized routing
- Handle asynchronous processing using message-driven architecture
- Design for scalability, fault tolerance, and resilience

## 🧩 Microservices Overview

The system is composed of the following independent microservices, each responsible for a specific business capability:

### 🔹 User Service
- Manages user registration and authentication
- Stores user credentials and roles
- Acts as the identity provider for the system

### 🔹 Product Service
- Manages product catalog and pricing
- Handles product stock and availability
- Designed for high read traffic and scalability

### 🔹 Order Service
- Core business service of the system
- Handles order creation and order lifecycle
- Communicates with User and Product services for validation
- Publishes order events for asynchronous processing

### 🔹 Notification Service
- Consumes order-related events from Kafka
- Processes notifications asynchronously
- Failure of this service does not impact order processing

### 🔹 API Gateway
- Acts as a single entry point for all client requests
- Routes requests to appropriate microservices
- Handles cross-cutting concerns such as security and logging

### 🔹 Discovery Service
- Provides service registration and discovery
- Enables dynamic lookup of microservice instances

### 🔹 Config Server
- Centralized configuration management
- Supports environment-specific configurations
- Eliminates the need to hardcode configuration in services

## High-Level Request Flow

1. Client (Web / Mobile / API Client) sends an HTTP request.
2. The request first reaches the API Gateway.
3. API Gateway:
   - Validates the request
   - Routes it to the appropriate microservice
4. The target microservice:
   - Processes business logic
   - Interacts with the database if required
5. If needed, the microservice communicates with other services using REST APIs.
6. The response is sent back through the API Gateway.
7. Client receives the final response.

## Database Design (Database Per Service)

| Service              | Database | Responsibility |
|----------------------|----------|----------------|
| user-service         | PostgreSQL | User accounts, roles, authentication data |
| product-service      | PostgreSQL | Product catalog, pricing, inventory |
| order-service        | PostgreSQL | Orders, order items, order status |
| notification-service | No DB / MongoDB (optional) | Email/SMS notification logs |
| discovery-service    | None | Service registration and discovery |
| config-server        | None | Centralized configuration |
| api-gateway          | None | Request routing and security |

## Service Communication

- API Gateway → Services: HTTP (REST)
- Order Service → User Service: REST (Feign Client)
- Order Service → Product Service: REST (Feign Client)
- Notification Service:
  - Triggered after order creation

## Tech Stack

- Java 8
- Spring Boot
- Spring Cloud (Gateway, Eureka, Config)
- Spring Data JPA
- PostgreSQL
- REST APIs
- Maven
- Git & GitHub
---------------------------------------------------------------------------Project----------------------------------------------------------------------------------------------------

🌐 Microservices with Config Server, Eureka & API Gateway

(Technical + Real-Time Scenario Notes)

1️⃣ Config Server (Centralized Configuration)
🔧 Technical Description

Spring Cloud Config Server is used to externalize and centralize configuration for all microservices.
Instead of keeping application.yml inside each service, configurations are stored in a Git repository and fetched at runtime.

🌍 Real-Time Scenario

Company example:
In a bank application, you have:

API Gateway

User Service

Order Service

Each service needs DB URL, ports, timeouts.

If DB password changes:
❌ Old way → Change in every service & redeploy
✅ Config Server way → Change once in Git → refresh services

📝 Daily Task Example

“Updated DB connection timeout in config repo without restarting services using Spring Cloud Config.”

2️⃣ Config Repository (GitHub)
🔧 Technical Description

A separate Git repository that stores configuration files like:

api-gateway.yml

user-service.yml

application.yml

Config Server reads from this repo.

🌍 Real-Time Scenario

Production setup:

Dev / QA / Prod use same code

Only config differs

Example:

user-service-dev.yml
user-service-prod.yml

📝 Daily Task Example

“Maintained environment-specific configuration in GitHub for microservices.”

3️⃣ Eureka Server (Service Discovery)
🔧 Technical Description

Eureka Server acts as a service registry.
All microservices register themselves with Eureka and discover each other dynamically.

🌍 Real-Time Scenario

Think of Eureka as a company receptionist:

New employee joins → receptionist notes their desk

Anyone asks → receptionist gives current location

If User Service restarts on a new port:
❌ Hardcoded URLs break
✅ Eureka updates automatically

📝 Daily Task Example

“Used Eureka for dynamic service discovery and load balancing.”

4️⃣ API Gateway (Single Entry Point)
🔧 Technical Description

Spring Cloud Gateway acts as a single entry point for all client requests and routes them to appropriate microservices.

🌍 Real-Time Scenario

Client (mobile/web app) never calls services directly.

Instead:

Client → API Gateway → User Service / Order Service


Gateway handles:

Routing

Security

Logging

Rate limiting

📝 Daily Task Example

“Implemented API Gateway routing to backend microservices using Spring Cloud Gateway.”

5️⃣ User Service (Business Logic)
🔧 Technical Description

User Service is a microservice that handles user-related operations such as:

User creation

User validation

User profile APIs

🌍 Real-Time Scenario

In an e-commerce app:

User logs in

User Service validates user

Other services trust this response

📝 Daily Task Example

“Developed REST APIs in User Service for user validation and profile management.”

6️⃣ How Request Flows (End-to-End)
🔁 Technical Flow
Browser → API Gateway → Eureka → User Service
                    ↓
               Config Server

🌍 Real-Time Example

User hits /users/test

Gateway asks Eureka → “Where is USER-SERVICE?”

Eureka responds with instance

Gateway forwards request

User Service returns response

📝 Daily Task Example

“Debugged end-to-end request flow between Gateway, Eureka, and backend services.”

7️⃣ Why We Use Config Import
🔧 Technical Description
spring:
  config:
    import: optional:configserver:http://localhost:8888


This tells the service:

“Load configuration from Config Server at startup.”

🌍 Real-Time Scenario

If Config Server is temporarily down:

App still starts (optional)

Prevents production outage

📝 Daily Task Example

“Integrated Config Server using spring.config.import for centralized configuration.”

8️⃣ Common Errors & What They Mean (Interview Gold)
❌ 404 at Gateway

➡ Route not configured or service not registered

❌ 400 Bad Request

➡ Controller expects input / validation fails

❌ Eureka Connection Refused

➡ Eureka server not running

📝 Daily Task Example

“Resolved routing and service discovery issues in Spring Cloud Gateway.”

9️⃣ Why This Architecture is Used in MNCs

✔ Independent deployments
✔ Centralized configuration
✔ High availability
✔ Easy scaling
✔ Cloud-native design

🌍 Real Companies

Amazon

Netflix

Flipkart

Paytm

Swiggy

🔑 One-Line Summary (Perfect for Notes)

“Designed a Spring Boot microservices architecture using Config Server, Eureka Service Discovery, and API Gateway for scalable and centralized backend systems.”