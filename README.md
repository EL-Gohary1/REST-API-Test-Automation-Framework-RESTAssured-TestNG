# ECommerceAPITests

A robust, production-grade **REST API test automation framework** for an E-Commerce application, built with Java, REST Assured, and TestNG. The framework follows a clean layered architecture with thread-safe parallel execution, data-driven testing, and integrated Allure reporting.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Architecture](#architecture)
- [Test Coverage](#test-coverage)
- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Viewing Reports](#viewing-reports)

---

## Overview

This project automates end-to-end API testing for an E-Commerce backend. It validates authentication flows, user profile management, password changes, and registration scenarios. The framework is designed with the following goals:

- **Thread-safety** — `ThreadLocal` API instances enable safe parallel test execution.
- **Fluent API design** — Recursive generics (`BaseApi<T extends BaseApi<T>>`) allow readable, chainable request building.
- **Data-driven testing** — TestNG `@DataProvider` classes supply varied input scenarios.
- **Centralized configuration** — A typed `config.properties` file manages environment-level settings via OWNER library.
- **Rich reporting** — Every request/response is captured automatically in Allure reports.

---

## Tech Stack

| Tool / Library             | Version   | Purpose                                   |
|----------------------------|-----------|-------------------------------------------|
| Java                       | 19        | Core language                             |
| REST Assured               | 5.5.6     | HTTP client & API assertion library       |
| TestNG                     | 7.11.0    | Test framework & parallel execution       |
| Jackson Databind           | 2.21.0    | JSON serialization / deserialization      |
| Lombok                     | 1.18.42   | Boilerplate reduction (getters, builders) |
| OWNER                      | 1.0.12    | Type-safe properties configuration        |
| JSON Schema Validator      | 5.5.6     | Response schema validation                |
| JavaFaker                  | 1.0.2     | Dynamic test data generation              |
| Allure TestNG              | 2.25.0    | Test reporting & visualization            |
| Allure REST Assured        | 2.25.0    | Auto-attach HTTP traffic to Allure        |
| Maven Surefire Plugin      | 3.1.2     | Test execution via Maven                  |

---

## Project Structure

```
ECommerceAPITests/
├── pom.xml                          # Maven build & dependency configuration
├── testng.xml                       # TestNG suite configuration (parallel execution)
└── src/
    ├── main/
    │   ├── resources/
    │   │   └── config.properties    # Environment configuration (base URL, credentials)
    │   └── java/com/mahmoudelgohary/ecommerce/
    │       ├── http/
    │       │   └── BaseApi.java     # Abstract base for all API clients (fluent builder)
    │       ├── apis/
    │       │   ├── auth/
    │       │   │   ├── LoginApi.java
    │       │   │   └── RegisterApi.java
    │       │   ├── user/
    │       │   │   ├── UserProfileApi.java
    │       │   │   └── ChangePasswordApi.java
    │       │   ├── cart/            # Cart API layer (reserved)
    │       │   └── product/         # Product API layer (reserved)
    │       ├── constants/
    │       │   ├── AuthPaths.java   # Enum: /login, /register endpoints
    │       │   ├── UserPaths.java   # Enum: /users/me, /users/me/password endpoints
    │       │   ├── ProductPaths.java
    │       │   ├── CartPaths.java
    │       │   ├── UserRole.java    # Enum: USER / ADMIN roles
    │       │   └── InvalidType.java
    │       ├── pojo/
    │       │   ├── request/         # Request body POJOs (LoginRequest, RegisterRequest, …)
    │       │   └── response/        # Response body POJOs
    │       ├── config/
    │       │   ├── PropertyConfig.java  # OWNER-based typed config interface
    │       │   └── PropertyUtil.java    # Static accessor for config properties
    │       └── util/
    │           ├── ApisRequestHelper.java  # Reusable request builder helpers
    │           ├── TestDataHelper.java     # JavaFaker wrapper (ThreadLocal)
    │           └── TokenManager.java       # Acquires & caches auth tokens per role
    └── test/
        └── java/com/mahmoudelgohary/ecommerce/
            ├── base/
            │   └── BaseTest.java          # Generic abstract test base (ThreadLocal, setup/teardown)
            ├── dataproviders/
            │   ├── LoginDataProvider.java
            │   └── RegisterDataProviders.java
            └── tests/
                ├── LoginApiTests.java
                ├── RegisterApiTests.java
                ├── ProfileSetupTests.java
                └── ChangePasswordTests.java
```

---

## Architecture

### Layer Diagram

```
┌─────────────────────────────────────────────┐
│              Test Layer                     │
│  LoginApiTests  |  RegisterApiTests         │
│  ProfileSetupTests  |  ChangePasswordTests  │
└────────────────────┬────────────────────────┘
                     │ uses
┌────────────────────▼────────────────────────┐
│             API Client Layer                │
│  LoginApi  |  RegisterApi                   │
│  UserProfileApi  |  ChangePasswordApi       │
│  (all extend BaseApi<T>)                    │
└────────────────────┬────────────────────────┘
                     │ uses
┌────────────────────▼────────────────────────┐
│              Support Layer                  │
│  TokenManager  |  ApisRequestHelper         │
│  TestDataHelper (JavaFaker)                 │
│  PropertyUtil / PropertyConfig              │
└─────────────────────────────────────────────┘
```

### Key Design Decisions

| Pattern | Details |
|---|---|
| **`BaseApi<T>`** | Abstract generic class using recursive generics for fluent, type-safe method chaining. All API clients extend it. |
| **`BaseTest<T>`** | Abstract generic test base using `ThreadLocal<T>` to give each parallel thread its own isolated API instance. |
| **Enum-based endpoints** | `AuthPaths`, `UserPaths`, `ProductPaths` enums bundle the URL path with its HTTP method to eliminate magic strings. |
| **`TokenManager`** | Centralized service that obtains and caches Bearer tokens per `UserRole`, avoiding redundant login calls across tests. |
| **`TestDataHelper`** | Thread-safe `ThreadLocal` Faker wrapper; `unload()` is called in `@AfterMethod` to prevent memory leaks. |
| **DataProviders** | TestNG `@DataProvider` classes (`LoginDataProvider`, `RegisterDataProviders`) decouple test data from test logic. |

---

## Test Coverage

### Authentication (`LoginApiTests`, `RegisterApiTests`)

| Test Method | Description |
|---|---|
| `validateLoginStatusCode` | Data-driven — validates HTTP status codes for valid/invalid login credentials |
| `validateBearerTokenInLoginResponse` | Asserts that a `token` field is present and non-null in a successful login response |
| `validateLoginFailsWithBearerAuth` | Asserts that using a Bearer token for `/login` returns `400 Bad Request` |
| `validateRegisterStatusCode` | Asserts `201 Created` on successful registration |
| `inValidateRegisterStatusCode` | Asserts error status for invalid/duplicate registration data |
| `validateRegisterResponse` | Validates the structure and fields of the registration response body |

### User Profile (`ProfileSetupTests`)

| Test Method | Description |
|---|---|
| `userCanViewProfile` | Asserts that an authenticated user can retrieve their own profile (`GET /users/me`) |
| `userCanUpdateProfile` | Asserts that an authenticated user can update allowed profile fields (`PUT /users/me`) |
| `userCanNotUpdateRole` | Asserts that attempting to update the `role` field is rejected |
| `userCanNotUpdateId` | Asserts that attempting to update the `id` field is rejected |
| `userCanNotUpdateEmail` | Asserts that attempting to update the `email` field is rejected |

### Password Management (`ChangePasswordTests`)

| Test Method | Description |
|---|---|
| `userCanChangePassword` | Asserts that an authenticated user can successfully change their password |

---

## Prerequisites

- **Java 19+** — [Download JDK](https://adoptium.net/)
- **Maven 3.8+** — [Download Maven](https://maven.apache.org/download.cgi)
- **Allure CLI** _(optional, for HTML reports)_ — [Install Allure](https://allurereport.org/docs/install/)
- **Running E-Commerce API server** on `http://localhost:3000`

---

## Configuration

All environment settings are managed in:

```
src/main/resources/config.properties
```

```properties
base.url=http://localhost:3000
Admin.email=admin@admin.com
Admin.password=123456789
User.email=user@gmail.com
User.password=12345678910
```

The properties are accessed via the **OWNER** library through a typed interface (`PropertyConfig`) and a static utility (`PropertyUtil.getProperty()`), so all configuration values are compile-time safe.

---

## Running Tests

### Run all tests via Maven

```bash
mvn clean test
```

### Run a specific test class

```bash
mvn clean test -Dtest=LoginApiTests
```

Maven Surefire is configured to read `testng.xml`, which runs all four test classes **in parallel** with **2 threads** (`parallel="methods" thread-count="2"`).

---

## Viewing Reports

After running the tests, Allure raw results are saved to `allure-results/`.

### Generate and open the HTML report

```bash
allure serve allure-results
```

The Allure report includes:
- Full request/response details for every test (auto-attached by `allure-rest-assured`)
- Pass/fail status per test with stack traces on failure
- Test suite grouping and timeline view
- Historical trends across multiple runs (when using `allure generate`)

---

## Author

**Mahmoud El-Gohary**  
Java Test Automation Engineer
