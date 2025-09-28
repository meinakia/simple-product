# Simple Product Application
## Overview
This application is a sample Spring Boot application for managing simple products. It demonstrates basic CRUD operations, database integration with PostgreSQL, Flyway migrations, and mapping between DTOs and entities using MapStruct.

## Prerequisites
Java 17

Maven

Docker (for PostgreSQL)

## Running the Application
1.Start PostgreSQL (for example, using Docker):
```text
docker run --name my-postgres -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres:14
```

2.Build and run the application:
```text
mvn clean install
mvn spring-boot:run
```
By default, the app runs on http://localhost:8080.

## API Endpoints
### Create a Product
Create a new product by passing the product name:
```text
curl -X POST http://localhost:8080/api/v1/products \
     -H "Content-Type: application/json" \
     -d '{"name": "HVAC"}'
```
Note: For simplicity, security is disabled and audit fields (createdBy and updatedBy) are hardcoded. In a production-ready application, these fields should be populated from the authenticated user's token.

### Get List of Products
Retrieve all products:
```text
curl -X GET http://localhost:8080/api/v1/products \
     -H "Content-Type: application/json"
```

## Database
- PostgreSQL is used as the database.
- Flyway handles database migrations automatically.
- For development, createdBy and updatedBy are set to a placeholder ID (1L).

## Security
- Security checks are disabled for this sample.
- Audit fields (createdBy and updatedBy) are hardcoded.
- In production, you should enable Spring Security and populate audit fields from the authenticated user.

## Future Improvements
- Enable authentication & authorization using Spring Security.
- Use JWT or session-based tokens to populate createdBy and updatedBy.
- Add update and delete endpoints.
- Add validation for product fields.
- Add error handling and exception mapping.