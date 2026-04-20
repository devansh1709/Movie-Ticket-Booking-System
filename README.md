# Movie Ticket Booking System

Backend system for a movie ticket booking platform built using Spring Boot.  
Provides REST APIs for managing movies, shows, and bookings.

---

## Tech Stack
- Java
- Spring Boot
- MySQL
- JPA / Hibernate

---

## Features
- Movie management (add, view movies)
- Booking system
- Seat allocation per show
- Exception handling with custom responses

---

## API Endpoints

### Movie APIs
- `POST /api/movies` → Create a movie
- `GET /api/movies` → Get all movies
- `GET /api/movies/{id}` → Get movie by ID

### Booking APIs
- `POST /api/bookings` → Create booking
- `GET /api/bookings/{id}` → Get booking details

---

## How to Run

1. Clone the repository  

2. Create a MySQL database:
```sql
CREATE DATABASE bms_db;
```

3. Update `application.properties`:
spring.datasource.username=your_username
spring.datasource.password=your_password

4. Run the Spring Boot application
Server runs on:
http://localhost:8081

---

## Testing
- APIs tested using Postman
- Import Postman collection (if provided)

---

## Limitations / Future Improvements
- No authentication (JWT)
- No payment gateway integration
- No concurrency handling for seat booking
- Frontend not implemented
