# Expense Tracker Application

A comprehensive REST API backend for expense tracking built with Spring Boot, Hibernate, and MySQL.

## Technologies Used

- **Java 17+**
- **Spring Boot 3.2.0**
- **Spring Data JPA (Hibernate)**
- **MySQL Database**
- **Maven**

## Features

- ✅ Create, Read, Update, Delete expenses
- ✅ Filter expenses by category
- ✅ Filter expenses by date range
- ✅ Generate expense reports with category breakdown
- ✅ RESTful API architecture
- ✅ Input validation
- ✅ Exception handling

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/expense-tracker.git
cd expense-tracker
```

### 2. Configure Database

Create a MySQL database:

```sql
CREATE DATABASE expense_tracker_db;
```

### 3. Configure Application Properties

Copy the example configuration file:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Update `application.properties` with your MySQL credentials:

```properties
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

**OR** set environment variables:

**Windows (Command Prompt):**
```cmd
set DB_USERNAME=root
set DB_PASSWORD=your_password
```

**Linux/Mac:**
```bash
export DB_USERNAME=root
export DB_PASSWORD=your_password
```

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will start at `http://localhost:8080`

## API Endpoints

### Base URL: `http://localhost:8080/api/expenses`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/expenses` | Create a new expense |
| GET | `/api/expenses` | Get all expenses |
| GET | `/api/expenses/{id}` | Get expense by ID |
| GET | `/api/expenses/category/{category}` | Get expenses by category |
| GET | `/api/expenses/date-range?startDate=&endDate=` | Get expenses by date range |
| PUT | `/api/expenses/{id}` | Update an expense |
| DELETE | `/api/expenses/{id}` | Delete an expense |
| GET | `/api/expenses/report?startDate=&endDate=` | Generate expense report |

## API Examples

### Create Expense

```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Grocery Shopping",
    "amount": 150.50,
    "date": "2026-01-04",
    "category": "Food",
    "notes": "Weekly groceries"
  }'
```

### Get All Expenses

```bash
curl http://localhost:8080/api/expenses
```

### Generate Report

```bash
curl "http://localhost:8080/api/expenses/report?startDate=2026-01-01&endDate=2026-01-31"
```

## Project Structure

```
expense-tracker/
├── src/
│   └── main/
│       ├── java/com/expensetracker/
│       │   ├── ExpenseTrackerApplication.java
│       │   ├── controller/
│       │   │   └── ExpenseController.java
│       │   ├── service/
│       │   │   └── ExpenseService.java
│       │   ├── repository/
│       │   │   └── ExpenseRepository.java
│       │   ├── entity/
│       │   │   └── Expense.java
│       │   ├── dto/
│       │   │   ├── ExpenseDTO.java
│       │   │   ├── CategoryReportDTO.java
│       │   │   └── ExpenseReportDTO.java
│       │   └── exception/
│       │       ├── ResourceNotFoundException.java
│       │       ├── ErrorResponse.java
│       │       └── GlobalExceptionHandler.java
│       └── resources/
│           └── application.properties
└── pom.xml
```

## Database Schema

### Expenses Table

| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key (auto-increment) |
| description | VARCHAR(255) | Expense description |
| amount | DECIMAL(10,2) | Expense amount |
| date | DATE | Expense date |
| category | VARCHAR(255) | Expense category |
| notes | VARCHAR(500) | Additional notes |
| created_at | DATE | Record creation date |
| updated_at | DATE | Record update date |

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Author

Bikram Roy Choudhury - ([https://github.com/biiikrammm](https://github.com/biiikrammm))

## Acknowledgments

- Spring Boot Documentation
- Hibernate ORM
- MySQL Database
