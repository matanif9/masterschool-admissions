# Masterschool Admissions API

This project implements the admissions flow system in Java using OOP principles and the Spark web framework.

## Design

The system is built around an abstract `Task` class. Each step in the admissions process is composed of one or more tasks. To support future flexibility, different types of tasks (such as condition-based or dynamic ones) can be implemented by extending the `Task` class. This makes it easy to add new logic without changing the core flow engine.

## How to Run

Requirements:
- Java 17+
- Maven

Run the server:
```
mvn compile exec:java
```

## Example Usage (PowerShell)

### 1. Create a new user
```powershell
$headers = @{ "Content-Type" = "application/json" }
$body = '{"email":"test@example.com"}'
$response = Invoke-RestMethod -Uri "http://localhost:4567/user" -Method POST -Headers $headers -Body $body
$userId = $response.user_id
```

### 2. Get the list of steps
```powershell
Invoke-RestMethod -Uri "http://localhost:4567/flow"
```

### 3. Get the current step of the user
```powershell
Invoke-RestMethod -Uri "http://localhost:4567/user/$userId/current"
```

### 4. Complete the IQ test task
```powershell
$taskBody = '{"task_name":"iq_test","score":82,"timestamp":"2025-03-17T20:10:00Z"}'
Invoke-RestMethod -Uri "http://localhost:4567/user/$userId/complete" -Method PUT -Headers $headers -Body $taskBody
```

### 5. Check the user's status
```powershell
Invoke-RestMethod -Uri "http://localhost:4567/user/$userId/status"
```
