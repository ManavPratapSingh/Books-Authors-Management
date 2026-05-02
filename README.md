# 📚 Book-Author Management System

A minimalist CRUD web application for managing **Books** and **Authors**, built with Spring Boot 4 and server-side rendered JSP views. Designed as a functional MVP with a clean layered architecture—no authentication, no Docker, just straightforward Spring.

---

## ✨ Features

- **Author Management** — Create, list, and edit authors with name and bio.
- **Book Management** — Create, list, and edit books linked to authors, with ISBN, genre, and description.
- **One-to-Many Relationship** — Each author can have many books; each book belongs to exactly one author.
- **Optimised Queries** — Custom `JOIN FETCH` queries prevent N+1 problems when listing books with their authors.
- **Server-Side Validation** — Hibernate Validator annotations enforce data integrity at the entity level.
- **Automatic Timestamps** — `createdAt` is auto-populated on book creation via `@CreationTimestamp`.
- **H2 Console** — In-memory database with a web console for quick inspection during development.

---

## 🛠 Tech Stack

| Layer         | Technology                                         |
| :------------ | :------------------------------------------------- |
| Framework     | Spring Boot **4.0.6**                              |
| Language      | Java **21**                                        |
| Build Tool    | Maven (with Maven Wrapper)                         |
| Database      | H2 (in-memory)                                     |
| ORM           | Spring Data JPA / Hibernate                        |
| View Layer    | JSP + JSTL                                         |
| Validation    | Hibernate Validator (Jakarta)                      |
| Boilerplate   | Lombok                                             |
| Testing       | JUnit 5, Mockito, MockMvc                          |

---

## 📁 Project Structure

```
book-author-management/
├── pom.xml
├── mvnw / mvnw.cmd                        # Maven Wrapper
├── AGENT.md                                # AI agent context file
│
└── src/
    ├── main/
    │   ├── java/com/manav/book_author_management/
    │   │   ├── BookAuthorManagementApplication.java   # Entry point
    │   │   ├── entity/
    │   │   │   ├── Author.java              # Author JPA entity
    │   │   │   └── Book.java                # Book JPA entity
    │   │   ├── repository/
    │   │   │   ├── AuthorRepository.java    # Author data access
    │   │   │   └── BookRepository.java      # Book data access + custom JOIN FETCH query
    │   │   ├── service/
    │   │   │   ├── AuthorService.java       # Author business logic
    │   │   │   └── BookService.java         # Book business logic
    │   │   └── controller/
    │   │       ├── AuthorController.java    # Author web endpoints
    │   │       └── BookController.java      # Book web endpoints
    │   ├── resources/
    │   │   └── application.yaml             # App configuration
    │   └── webapp/WEB-INF/jsp/
    │       ├── authors/
    │       │   ├── list.jsp                 # Author listing page
    │       │   └── form.jsp                 # Author create/edit form
    │       └── books/
    │           ├── list.jsp                 # Book listing page
    │           └── form.jsp                 # Book create/edit form
    │
    └── test/java/com/manav/book_author_management/
        ├── BookAuthorManagementApplicationTests.java   # Context load test
        ├── BookIntegrationTest.java                    # MockMvc integration test
        └── service/
            └── AuthorServiceTest.java                 # Unit test with Mockito
```

---

## 🏗 Domain Model

```
┌──────────────┐        1   ╌╌╌╌╌   *  ┌──────────────────┐
│    Author     │───────────────────────│       Book        │
├──────────────┤                        ├──────────────────┤
│ id       Long│                        │ id       Long     │
│ name   String│                        │ name     String   │
│ bio    String│                        │ isbn     String   │
│ books  List  │                        │ genre    String   │
└──────────────┘                        │ description String│
                                        │ createdAt DateTime│
                                        │ author   Author   │
                                        └──────────────────┘
```

**Relationship:** `Author` (1) → (∗) `Book`  
Mapped via `@OneToMany` / `@ManyToOne` with `@JoinColumn(name = "author_id")`.

### Validation Rules

| Entity | Field         | Constraints                                     |
| :----- | :------------ | :---------------------------------------------- |
| Author | `name`        | `@NotBlank`, `@Size(min=2, max=100)`            |
| Author | `bio`         | `@Size(max=500)`                                |
| Book   | `name`        | `@NotBlank`, `@Size(min=1, max=200)`            |
| Book   | `isbn`        | `@NotBlank`                                     |
| Book   | `genre`       | `@NotBlank`                                     |
| Book   | `description` | `@Size(max=1000)`                               |
| Book   | `createdAt`   | Auto-generated via `@CreationTimestamp`          |

---

## 🚀 Getting Started

### Prerequisites

- **Java 21** or later
- **Maven 3.9+** (or use the included Maven Wrapper)

### Run the Application

```bash
# Using Maven Wrapper (no Maven installation needed)
./mvnw spring-boot:run

# Or with a system Maven installation
mvn spring-boot:run
```

The application starts on **`http://localhost:8080`**.

### Access Points

| URL                        | Description                 |
| :------------------------- | :-------------------------- |
| `http://localhost:8080/authors`   | Author listing page   |
| `http://localhost:8080/authors/new` | Create a new author |
| `http://localhost:8080/books`     | Book listing page     |
| `http://localhost:8080/books/new` | Create a new book     |
| `http://localhost:8080/h2-console` | H2 database console  |

#### H2 Console Credentials

| Setting   | Value                |
| :-------- | :------------------- |
| JDBC URL  | `jdbc:h2:mem:bookdb` |
| Username  | `sa`                 |
| Password  | `password`           |

---

## 🔗 API Endpoints

All endpoints return server-rendered JSP views (not JSON).

### Authors

| Method | Path                   | Action                  |
| :----- | :--------------------- | :---------------------- |
| GET    | `/authors`             | List all authors        |
| GET    | `/authors/new`         | Show create form        |
| POST   | `/authors`             | Submit new author       |
| GET    | `/authors/edit/{id}`   | Show edit form          |
| POST   | `/authors/update`      | Submit author update    |

### Books

| Method | Path                 | Action                          |
| :----- | :------------------- | :------------------------------ |
| GET    | `/books`             | List all books (with authors)   |
| GET    | `/books/new`         | Show create form                |
| POST   | `/books`             | Submit new book                 |
| GET    | `/books/edit/{id}`   | Show edit form                  |
| POST   | `/books/update`      | Submit book update              |

> **Note:** Deletion is intentionally out of scope for this MVP.

---

## 🧪 Testing

The project includes both unit and integration tests.

### Run All Tests

```bash
./mvnw test
```

### Test Coverage

| Test Class               | Type        | What It Verifies                                                  |
| :----------------------- | :---------- | :---------------------------------------------------------------- |
| `AuthorServiceTest`      | Unit        | Service layer logic with mocked repositories (Mockito)            |
| `BookIntegrationTest`    | Integration | Full CRU flow via MockMvc — form rendering, submission, redirect  |

**Unit tests** mock the repository layer and verify service method behaviour in isolation.  
**Integration tests** use `@SpringBootTest` + `MockMvc` to exercise the controller → service → repository chain end-to-end with an H2 database.

---

## ⚙️ Configuration

All configuration lives in `src/main/resources/application.yaml`:

```yaml
spring:
  mvc:
    view:
      prefix: /WEB-INF/jsp/
      suffix: .jsp
  datasource:
    url: jdbc:h2:mem:bookdb
    driverClassName: org.h2.Driver
    username: sa
    password: password
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

Key points:
- **JSP resolution** is configured via `spring.mvc.view.prefix` and `suffix`.
- **DDL auto-generation** is set to `update` — Hibernate creates/updates tables on startup.
- **SQL logging** is enabled for development visibility.

---

## 📌 Design Decisions

1. **No Security** — All endpoints are public. This is an intentional MVP constraint.
2. **No Delete** — Only Create, Read, and Update operations are implemented.
3. **In-Memory Database** — Data resets on every restart. Ideal for development and demos.
4. **JOIN FETCH** — `BookRepository.getAllBooksWithAuthors()` uses a custom JPQL query to eagerly load author data in a single query, avoiding the N+1 select problem on the book listing page.
5. **Lombok** — Reduces boilerplate with `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`, and `@RequiredArgsConstructor`.
6. **Maven Wrapper** — Included so the project builds without a pre-installed Maven.

---

## 📄 License

This project is for educational / personal use.
