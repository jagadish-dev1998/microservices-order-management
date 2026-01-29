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
