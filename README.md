# Patient Management System

A comprehensive, microservices-based patient management system built with Spring Boot, featuring event-driven architecture, cloud-native deployment, and modern software engineering practices.

## 🏗️ Architecture Overview

This system follows a **microservices architecture** pattern with the following key characteristics:

- **Event-driven communication** via Apache Kafka
- **Synchronous communication** via REST APIs and gRPC
- **Cloud-native deployment** with AWS CDK infrastructure
- **Containerized services** using Docker
- **Resilient design** with circuit breakers and retry mechanisms

## 🚀 Services

### Core Services

| Service                 | Port                     | Java Version | Description                                        |
| ----------------------- | ------------------------ | ------------ | -------------------------------------------------- |
| **Patient Service**     | 4000                     | 17           | Patient CRUD operations, caching, event publishing |
| **Auth Service**        | 4005                     | 17           | JWT authentication and user management             |
| **Appointment Service** | -                        | 21           | Appointment scheduling and management              |
| **Billing Service**     | 4001 (HTTP), 9001 (gRPC) | 17           | Billing account management via gRPC                |
| **Analytics Service**   | 4002                     | 17           | Event analytics and reporting                      |
| **API Gateway**         | 4004                     | 17           | Centralized routing and security                   |

## 🛠️ Technology Stack

### Backend Framework

- **Spring Boot** 3.5.4/3.4.5
- **Spring Cloud Gateway** 2025.0.0
- **Spring Data JPA** with Hibernate
- **Spring Security** with JWT
- **Spring Kafka** for event streaming
- **Spring Boot Actuator** for monitoring

### Programming Languages

- **Java 17** (most services)
- **Java 21** (appointment service)

### Build & Dependency Management

- **Maven** 3.9.9
- **Multi-stage Docker builds**

### Database & Persistence

- **PostgreSQL** 17.2 (production)
- **H2 Database** (development/testing)
- **Spring Data JPA** with Hibernate
- **Redis** for caching and rate limiting

### Communication Protocols

- **REST APIs** (JSON over HTTP)
- **gRPC** with Protocol Buffers
- **Apache Kafka** for event streaming

### Message Brokers & Events

- **Apache Kafka** 2.8.0
- **Protocol Buffers** 4.29.1
- **Event-driven architecture**

### Caching & Performance

- **Redis** cluster
- **Spring Boot Cache**
- **Circuit Breaker** (Resilience4j 2.3.0)

### Security & Authentication

- **JWT (JSON Web Tokens)**
- **Spring Security**
- **Rate Limiting** with Redis

### API Documentation

- **OpenAPI 3.0** (Swagger)
- **SpringDoc OpenAPI** 2.6.0

### Monitoring & Observability

- **Prometheus** metrics
- **Micrometer** registry
- **Spring Boot Actuator**
- **Health checks** and monitoring endpoints

### Infrastructure & Cloud

- **AWS CDK** 2.178.1
- **AWS ECS Fargate**
- **AWS RDS PostgreSQL**
- **AWS ElastiCache Redis**
- **AWS MSK (Managed Kafka)**
- **AWS VPC** with multi-AZ setup
- **AWS Application Load Balancer**
- **AWS CloudMap** for service discovery

### Containerization

- **Docker** with multi-stage builds
- **OpenJDK 21** runtime
- **Maven 3.9.9** build environment

### Testing

- **JUnit 5** 5.11.4
- **Spring Boot Test**
- **REST Assured** 5.3.0 for integration testing
- **TestContainers** support

### Development Tools

- **Spring Boot DevTools**
- **H2 Console** for development
- **LocalStack** for local AWS emulation

## 📡 Communication Patterns

### Synchronous Communication

- **REST APIs**: Service-to-service HTTP communication
- **gRPC**: High-performance RPC calls (Patient ↔ Billing services)

### Asynchronous Communication

- **Kafka Events**:
  - Patient events: `patient-service` → `analytics-service`
  - Billing events: `patient-service` → `billing-service`

## 🏛️ Infrastructure Components

### AWS Resources (via CDK)

- **VPC**: Multi-AZ setup with public/private subnets
- **ECS Cluster**: Fargate services for all microservices
- **RDS**: Dedicated PostgreSQL instances per service
- **ElastiCache**: Redis cluster for caching and rate limiting
- **MSK**: Managed Kafka cluster for event streaming
- **Service Discovery**: CloudMap namespace (`patient-management.local`)
- **Load Balancer**: Public-facing ALB for API Gateway
- **Security Groups**: Network-level security controls

### Local Development

- **LocalStack**: AWS service emulation
- **Docker Compose**: Local service orchestration
- **H2 Database**: In-memory database option

## 📊 Monitoring & Observability

- **Metrics Collection**: Micrometer + Prometheus
- **Health Monitoring**: Database and service health checks
- **Structured Logging**: AWS CloudWatch log groups
- **Performance Metrics**: Response times, throughput, error rates
- **Circuit Breaker Status**: Resilience4j metrics

## 🧪 Testing Strategy

- **Unit Tests**: JUnit 5 with Spring Boot Test
- **Integration Tests**: REST Assured for API testing
- **API Testing**: HTTP request files for manual testing
- **gRPC Testing**: HTTP-based gRPC testing
- **Contract Testing**: Service interface validation

## 🔄 Data Flow Examples

### Patient Creation Flow

1. **REST API** → API Gateway (JWT validation)
2. **API Gateway** → Patient Service
3. **Patient Service** → PostgreSQL (persistence)
4. **Patient Service** → Redis (caching)
5. **Patient Service** → Kafka (patient events)
6. **Patient Service** → Billing Service (gRPC)
7. **Analytics Service** ← Kafka (event consumption)

### Authentication Flow

1. **Login Request** → Auth Service
2. **Auth Service** → PostgreSQL (user validation)
3. **Auth Service** → JWT token generation
4. **API Requests** → API Gateway (JWT validation)
5. **API Gateway** → Target Service

## 🚀 Getting Started

### Prerequisites

- Java 17+ and 21
- Maven 3.9+
- Docker
- AWS CLI (for production deployment)
- LocalStack (for local development)

### Local Development

```bash
# Clone the repository
git clone https://github.com/aryan2574/patient-management.git
cd patient-management

# Start LocalStack services
cd infrastructure
./localstack-deploy.sh

# Build and run services
cd ../patient-service
mvn clean package
docker build -t patient-service .
docker run -p 4000:4000 patient-service

# Similar process for other services...
```

### API Testing

Use the provided HTTP request files in `api-requests/` directory:

- `api-requests/auth-service/login.http`
- `api-requests/patient-service/create-patient.http`
- `grpc-requests/billing-service/create-billing-account.http`

## 🎯 Key Features

- **Microservices Architecture**: Clear service boundaries and responsibilities
- **Event-Driven Design**: Loose coupling via Kafka events
- **Cloud-Native**: AWS CDK infrastructure as code
- **Resilient**: Circuit breakers, retry mechanisms, and fallbacks
- **Observable**: Comprehensive monitoring and metrics
- **Secure**: JWT authentication and rate limiting
- **Scalable**: Containerized services with load balancing
- **API-First**: RESTful APIs with OpenAPI documentation

## 🔮 Future Enhancements

- **Service Mesh**: Istio/Linkerd integration
- **Distributed Tracing**: Jaeger/Zipkin
- **Centralized Configuration**: Spring Cloud Config
- **API Versioning**: Explicit versioning strategy
- **Database Migrations**: Flyway/Liquibase
- **Advanced Security**: OAuth2/OIDC integration
- **Contract Testing**: Pact integration
- **Chaos Engineering**: Fault injection testing

## 📚 Additional Resources

- **API Documentation**: Available at `/api-docs/{service}` endpoints
- **Health Checks**: `/actuator/health` endpoints
- **Metrics**: `/actuator/prometheus` endpoints
- **Service Discovery**: CloudMap namespace configuration
