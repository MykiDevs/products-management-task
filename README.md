# Products Management Task

REST API for managing products and producers, built with Spring Boot and PostgreSQL.

## Tech Stack

- Java 25
- Spring Boot 3
- PostgreSQL + Liquibase
- MapStruct, Lombok

## Prerequisites

- Java 25
- Docker (or a local PostgreSQL instance)

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/MykiDevs/products-management-task.git
cd products-management-task
```

### 2. Start PostgreSQL

```bash
docker run -d \
  --name products-db-task \
  -e POSTGRES_DB=db \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=password \
  -p 5432:5432 \
  postgres:latest
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The app starts on `http://localhost:8080`.

## API Overview
### Producers

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/producers/new` | Create a producer |
| GET | `/api/v1/producers/{id}` | Get producer by ID |
| GET | `/api/v1/producers/` | List producers (paginated) |
| PUT | `/api/v1/producers/{id}` | Update a producer |
| DELETE | `/api/v1/producers/{id}` | Delete a producer (cascades to products) |

### Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/products/new` | Create a product |
| GET | `/api/v1/products/{id}` | Get product by ID |
| GET | `/api/v1/products/` | List products (paginated) |
| PUT | `/api/v1/products/{id}` | Update a product |
| DELETE | `/api/v1/products/{id}` | Delete a product |

**Pagination query params:** `page`, `size`, `sortBy`, `direction` (ASC/DESC)

### Example — Create a producer

```http
POST /api/v1/producers/new

{
  "name": "Example Inc"
}
```

### Example — Create a product

```http
POST /api/v1/products/new

{
  "name": "Example",
  "price": 999,
  "producerId": 1,
  "attributes": {
    "color": "red",
    "field": "example"
  }
}
```
