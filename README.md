# Finance Tracker API

A REST API for personal financial tracking, built with Java and Spring Boot.

The project manages accounts, transaction categories, and financial transactions. It was developed to practice RESTful
design, relational persistence, DTOs, validation, pagination, ordering, and business rules with Spring Boot.

## Technologies

- Java 26
- Spring Boot
- Spring Web MVC
- Spring Data JPA / Hibernate
- Jakarta Validation
- MySQL 8.4
- Docker Compose
- Gradle
- Lombok
- JUnit, MockMvc, and AssertJ
- Git Actions

## Features

- Create accounts with an initial balance and account type
- Update the account name and account type fields
- Create income and expense categories
- Create transactions associated with an account and category
- Validate that a transaction type matches its category type
- List accounts and categories with custom ordering
- List transactions with pagination and ordering
- Retrieve an account's currently stored balance value
- Persist account, category, and transaction relationships in MySQL
- Validate incoming requests and return centralized error responses
- Run automated tests for core API flows

## Domain model

```text
Account 1 ─── * Transaction * ─── 1 Category
```

Each transaction belongs to one account and one category. The database stores these relationships through foreign keys:

- `transaction.account_id` → `account.id`
- `transaction.category_id` → `category.id`

Categories and transactions use either `INCOME` or `EXPENSE`. The API rejects a transaction when its type is
incompatible with the selected category.

## API base URL

```text
http://localhost:8081/finance
```

## Endpoints

| Method | Endpoint                                      | Description                                            |
|--------|-----------------------------------------------|--------------------------------------------------------|
| `POST` | `/account`                                    | Creates an account                                     |
| `POST` | `/category`                                   | Creates a category                                     |
| `POST` | `/transaction`                                | Creates a transaction                                  |
| `PUT`  | `/account/{id}`                               | Update account filds                                   |
| `GET`  | `/accounts?sortBy={field}`                    | Lists accounts ordered by `name`, `type`, or `balance` |
| `GET`  | `/categories?sortBy={field}`                  | Lists categories ordered by `name` or `type`           |
| `GET`  | `/transactions?page=0&size=10&sortBy={field}` | Lists transactions with pagination and ordering        |
| `GET`  | `/balance/{accountId}`                        | Retrieves an account's stored balance value            |

Transaction ordering accepts: `description`, `amount`, `type`, and `date`.

## Request examples

### Create an account

```http
POST /finance/account
Content-Type: application/json

{
  "name": "Main account",
  "type": "CHECKING_ACCOUNT",
  "initialBalance": 2500.00
}
```

Available account types:

- `CHECKING_ACCOUNT`
- `SAVINGS_ACCOUNT`
- `CREDIT_CARD`
- `CASH`
- `DIGITAL_WALLET`

### Create a category

```http
POST /finance/category
Content-Type: application/json

{
  "name": "Groceries",
  "type": "EXPENSE"
}
```

### Create a transaction

Replace `accountId` and `categoryId` with IDs returned by the account and category creation endpoints.

```http
POST /finance/transaction
Content-Type: application/json

{
  "description": "Monthly grocery shopping",
  "amount": 350.00,
  "type": "EXPENSE",
  "accountId": "00000000-0000-0000-0000-000000000000",
  "categoryId": "00000000-0000-0000-0000-000000000000"
}
```

A transaction receives its creation date from the application.

## Running locally

### Requirements

- Java 26
- Docker Desktop with Docker Compose

### 1. Clone the repository

```bash
git clone https://github.com/NeylonEduardo/finance-tracker-api.git
cd finance-tracker-api
```

### 2. Create the local environment file

Linux or macOS:

```bash
cp .env.example .env
```

Windows PowerShell:

```powershell
Copy-Item .env.example .env
```

Update the placeholder passwords in `.env`. This file is intentionally ignored by Git and must not be committed.

### 3. Start MySQL

```bash
docker compose up -d
docker compose ps
```

### 4. Run the application

Windows PowerShell:

```powershell
.\gradlew.bat bootRun
```

Linux or macOS:

```bash
./gradlew bootRun
```

The API will be available at `http://localhost:8081`.

## Running tests

Windows PowerShell:

```powershell
.\gradlew.bat test
```

Linux or macOS:

```bash
./gradlew test
```

## Concepts practiced

- REST APIs and HTTP status codes
- Controller, application/use-case, repository, entity, domain ID, and DTO separation
- Dependency injection
- Spring Data JPA and MySQL persistence
- `@ManyToOne` relationships and foreign keys
- UUID identifiers with `@EmbeddedId`
- Request validation and centralized exception handling
- Transactions with `@Transactional`
- Pagination and sorting with Spring Data
- Docker Compose and environment-based configuration
- Automated API tests with MockMvc

## Author

**Neylon Eduardo**

Computer Science student focused on Java, Spring Boot, and backend development.
