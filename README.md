# 📚 Library Management System

A full-stack web application for managing library operations such as book inventory, user registration, and transaction tracking. 
This system supports secure login, role-based access (Admin/User), and real-time updates using RESTful APIs.

---

## 🚀 Features

- 📖 Book Management (Add, View, Update, Delete)
- 👤 User Management with Role-based Access
- 🔐 Authentication using JWT and Spring Security
- 🔄 Issue/Return Book Transactions
- 🌐 RESTful API integration between frontend and backend

---

## 🛠️ Tech Stack

### ✅ Frontend:
- HTML5
- CSS3
- JavaScript 

### ✅ Backend:
- Java with Spring Boot
- RESTful API architecture
- Spring Security + JWT (Authentication & Authorization)

### ✅ Database:
- MySQL 

---

## 📂 Project Structure

/library-management
│
├── backend/
│ ├── src/main/java/com/example/library/
│ ├── controllers/
│ ├── services/
│ ├── models/
│ └── repositories/
│
├── frontend/
│ ├── index.html
│ ├── styles.css
│ └── app.js
│
├── README.md
└── database.sql

pgsql
Copy
Edit

---

## 🔐 Authentication Flow

- Users register/login using credentials
- JWT is generated on login and sent with each API request
- Spring Security verifies the token and authorizes access based on role

---

## 📦 API Endpoints

| Method | Endpoint                | Description                |
|--------|-------------------------|----------------------------|
| GET    | `/api/books`            | Get all books              |
| POST   | `/api/books`            | Add a new book             |
| PUT    | `/api/books/{id}`       | Update book info           |
| DELETE | `/api/books/{id}`       | Delete a book              |
| POST   | `/auth/register`        | Register a new user        |
| POST   | `/auth/login`           | User login (JWT issued)    |

---

## 🧪 How to Run Locally

### 1. Clone the Repository
```bash
git clone https://github.com/YourUsername/library-management.git
cd library-management

2. Start Backend (Spring Boot)
cd backend
./mvnw spring-boot:run

3. Start Frontend
cd frontend
npx serve .
