# Task Tool

`task-tool` is a Spring Boot REST API for managing tasks. It supports task creation, listing, lookup by id, status updates, deletion, and generated OpenAPI documentation. The application now uses PostgreSQL for normal operation, with local infrastructure provided through Docker Compose and schema changes managed by Liquibase.

## Overview

This project exposes a simple task management API with a Clean Architecture approach:

- `entrypoint`: application entry points, for now only `rest`
- `core`: domain models, service logic, and gateway contracts
- `external`: external integrations, for now only `db`

## Tech Stack

- Java 25
- Gradle 9 via the included wrapper
- Spring Boot 4.1
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Liquibase
- Springdoc OpenAPI with Swagger UI
- JaCoCo coverage reporting
- Docker Compose for local PostgreSQL
- Actuator

## Prerequisites

You need:

- JDK 25 installed, or a local environment capable of satisfying the Gradle toolchain
- Docker and Docker Compose to run the local PostgreSQL container
- A shell capable of running `./gradlew`

## Running PostgreSQL Locally

Start PostgreSQL with:

```bash
docker compose up -d
```

Stop it with:

```bash
docker compose down
```

The container uses these defaults:

- Host: `localhost`
- Port: `5432`
- Database: `task_tool`
- Username: `task_tool`
- Password: `task_tool`

The database data is stored in the named Docker volume `postgres-data`.

Connection example:

```bash
psql postgresql://task_tool:task_tool@localhost:5432/task_tool
```

## Running the Application

Start PostgreSQL first, then run the API:

```bash
./gradlew bootRun
```

By default, the application runs on:

- API base URL: `http://localhost:8080`

The application reads database settings from environment variables. If you do not override them, it uses the same defaults as `docker-compose.yml`.

Available variables:

- `DB_HOST` default `localhost`
- `DB_PORT` default `5432`
- `DB_NAME` default `task_tool`
- `DB_USERNAME` default `task_tool`
- `DB_PASSWORD` default `task_tool`

Example override:

```bash
DB_HOST=localhost DB_PORT=5432 DB_NAME=task_tool DB_USERNAME=task_tool DB_PASSWORD=task_tool ./gradlew bootRun
```

## Database Management

Schema creation and evolution are managed with Liquibase.

- Changelog entrypoint: `src/main/resources/db/changelog/db.changelog-master.yaml`
- Hibernate DDL mode: `validate`
- SQL init scripts: disabled for normal startup

On application startup, Liquibase creates the required tables before JPA validation runs.

## Running Tests

Run the automated tests with:

```bash
./gradlew test
```

This also generates JaCoCo coverage reports for the current test suite.

Generate coverage reports explicitly with:

```bash
./gradlew jacocoTestReport
```

Coverage outputs:

- HTML report: `build/reports/jacoco/test/html/index.html`
- XML report: `build/reports/jacoco/test/jacocoTestReport.xml`

The XML report is suitable for CI tooling and coverage upload steps.

## API Notes

The task listing endpoint supports pagination and optional status filtering:

- `GET /api/v1/tasks?page=0&size=20`
- `GET /api/v1/tasks?status=TODO`

## API Documentation

With application running locally, generated API docs are available at:

- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

Swagger UI complements the existing Postman collection. Use it for quick contract discovery and interactive local requests.

## Postman

The repository includes Postman assets for manual API testing:

- `postman/task-tool.postman_collection.json`
- `postman/task-tool.postman_environment.json`

## Current Limitations

- No authentication or authorization
- The API is focused on basic CRUD operations for tasks
- Coverage thresholds are not enforced yet
