# Task Management System

A full-stack web application built with Spring Boot that allows users to manage their daily tasks efficiently.

## Features

- User Registration and Login with Spring Security
- BCrypt Password Encryption
- Create, Read, Update, Delete Tasks
- Task Priority (High, Medium, Low)
- Task Status (Pending, In Progress, Completed)
- Responsive UI with Bootstrap 5
- MySQL Database with JPA/Hibernate

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Spring Boot 3, Spring MVC, Spring Security |
| Database | MySQL, Spring Data JPA, Hibernate |
| Frontend | Thymeleaf, Bootstrap 5 |
| Build Tool | Maven |
| Language | Java 17 |

## Project Structure
src/main/java/com/ansh/taskmanager/

├── controller/        # Handles HTTP requests

├── service/           # Business logic

├── repository/        # Database operations

├── entity/            # Database models

├── dto/               # Data transfer objects

├── security/          # Spring Security config

└── exception/         # Global exception handling

## Setup Instructions

### Prerequisites
- Java 17+
- MySQL 8+
- Maven

### Steps

1. Clone the repository
```bash
git clone https://github.com/yourusername/task-management-system.git
cd task-management-system
```

2. Create the database
```sql
CREATE DATABASE taskmanager_db;
```

3. Configure properties
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```
Then edit `application.properties` with your MySQL credentials.

4. Run the application
```bash
mvn spring-boot:run
```

5. Open in browser
http://localhost:8080/register

## Screenshots

> Register, Login, and Dashboard screenshots can be added here.

## Author

**Ansh Singh**  
[GitHub](https://github.com/AnshSingh24)