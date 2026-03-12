## 🏥 Patient Management System (Microservices Architecture)
A robust, scalable healthcare backend system leveraging Microservices, gRPC, and Spring Boot 3 to ensure high-performance patient data handling and system resilience.

## 📖 Overview
This project is a backend ecosystem designed to manage patient records, billing, and clinical data across multiple independent services. The core focus is on achieving low-latency communication and strict data consistency in a distributed environment.

## 🏗 System Architecture**
The system is built with a Microservices-first approach, splitting business logic into specialized domains:

**Patient Service:** Manages core patient demographics and medical history using Spring Boot.

**Billing/Internal Service:** Handles financial records and invoice generation.

**Inter-service Communication:** Utilizes gRPC and Protocol Buffers for high-performance, synchronous internal calls instead of traditional REST, significantly reducing overhead.

**Database:** Each service maintains its own PostgreSQL instance to ensure loose coupling (Database-per-service pattern).

## 🚀 Engineering Highlights
**1. High-Performance Communication (gRPC)**
**Challenge**: Reducing latency in internal service-to-service calls.

**Solution**: Integrated gRPC to leverage HTTP/2 and binary serialization, resulting in faster data transmission compared to JSON/REST.

**2. Infrastructure & Deployment**
**Containerization**: Both services and their respective databases are fully Dockerized.

**Orchestration**: Managed via Docker Compose for easy local development and consistent environment parity.

**3. Security & Resilience**
**IAM**: Implementing secure access patterns to protect sensitive patient information.

**Resilience**: Designed with a focus on fault isolation, ensuring that a failure in the billing service does not bring down the patient record system.

## 🛠 Technical Stack
**Language/Framework**: Java, Spring Boot 3.

**Protocols**: gRPC, RESTful APIs.

**Data Management**: PostgreSQL.

**DevOps**: Docker, Docker Compose, GitHub Actions.

## 🛠 Setup & Installation
**Bash**
# Clone the repository
git clone https://github.com/heaven-vo/microservices-patient-billing

# Build and start the entire ecosystem
docker-compose up --build
