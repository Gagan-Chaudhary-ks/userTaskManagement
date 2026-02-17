# User-Task Management Console Application

A Java-based console application that manages users and their tasks using JDBC and MySQL.  
The system supports user management, task assignment, task updates, and safe deletion with transaction handling.

---

## 🚀 Features

### 👤 User Management
- Add new users
- View all users
- Delete users (with transaction & rollback support)

### 📋 Task Management
- Add task to a user
- View tasks by user
- Update task status (PENDING / IN_PROGRESS / COMPLETED)
- Delete task with confirmation
- Strict ownership validation (task must belong to user)

---

## 🛡 Data Safety & Integrity

- Manual transaction management for user deletion
- Rollback support on failure
- Referential integrity maintained
- Defensive input validation (prevents InputMismatchException)
- Case-insensitive confirmation handling

---

## 🏗 Architecture

- Service-based structure (`UserService`, `TaskService`)
- Dependency injection (Connection & Scanner)
- Clean separation of concerns
- Structured CLI navigation (Main Menu → User Menu → Task Menu)

---

## 🧰 Technologies Used

- Java
- JDBC
- MySQL
- Console-based UI

---

## 🗄 Database Schema

### users
- id (Primary Key)
- name
- email

### tasks
- task_id (Primary Key)
- user_id (Foreign Key → users.id)
- title
- status 

Foreign key ensures referential integrity between users and tasks.

---

## ▶ How to Run

1. Create MySQL database:
   ```sql
   CREATE DATABASE user_task_db;
   ```
2. Create the required tables (users and tasks) inside the database.
3. Update database credentials inside Application.java before running the application. 
4. Build the project using Maven:
```
mvn clean install
```
5. Run the application: 
```
mvn exec:java
```
(or run `Application.java` from your IDE)


---

## 🎯 Author

Gagan Chaudhary  
B.Tech (Electronics & Communication Engineering)  
Aspiring Software Developer


