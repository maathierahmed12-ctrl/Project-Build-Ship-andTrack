<img width="1260" height="709" alt="image" src="https://github.com/user-attachments/assets/869a263b-9e0f-4b65-ab54-c4a0c60439cd" />
# Visitor Log API 

A lightweight, in-memory Java microservice built with the **Spring Boot** framework to manage, record, and track front desk visitors at a training center or corporate office.

---

 Key Features
*   **Spring Boot** core architecture.
*   **In-Memory Storage**: Instant data retrieval and state management.
*   **Docker Ready**: Fully containerized and optimized to build, ship, and run anywhere.
*   **RESTful Design**: Standard HTTP methods supporting clean JSON payloads.

---

API Endpoint List

All request and response bodies utilize the standard **JSON** format.

| HTTP Method | Endpoint | Request Body | Description |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/visitors` | *None* | Retrieve all registered visitors. |
| **GET** | `/api/visitors/{id}` | *None* | Get details of a specific visitor using their unique `ID` as a path variable. |
| **POST** | `/api/visitors` | `{"name": "string", "company": "string", "purpose": "string"}` | Log and save a new visitor. |

---

 How to Run the Project

You can run this project either **locally on your machine** or **containerized via Docker** (locally or on your remote server).

 Option A: Running Locally (Without Docker)

Ensure you have **Java 17 (or newer)** installed on your machine.

1. **Build the Application Archive (JAR):**
   * **Windows (PowerShell):**
     ```powershell
     .\mvnw.cmd clean package -DskipTests
     ```
   * **Linux / macOS:**
     ```bash
     ./mvnw clean package -DskipTests
     ```

2. **Run the Executable JAR:**
   ```bash
   java -jar target/brand-new-0.0.1-SNAPSHOT.jar
