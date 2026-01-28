# Patient Management System (Microservices)

Dự án quản lý bệnh nhân sử dụng kiến trúc Microservices với Spring Boot, gRPC và PostgreSQL, được triển khai và quản lý bằng Docker.

## 🛠 Công nghệ sử dụng
* **Java 17/21 & Spring Boot**: Framework chính cho các dịch vụ.
* **gRPC**: Giao tiếp hiệu năng cao giữa các service (Billing và Patient).
* **PostgreSQL**: Hệ quản trị cơ sở dữ liệu cho Patient Service.
* **Docker & Docker Network**: Đóng gói và kết nối các container.

## 🏗 Cấu trúc hệ thống
1. **patient-service**: API chính quản lý thông tin bệnh nhân (Cổng 4000).
2. **billing-service**: Xử lý hóa đơn, nhận lệnh qua gRPC (Cổng 4001, gRPC Port 9001).
3. **patient-service-db**: Container chạy PostgreSQL cho hệ thống.


