# Clothing Shop Inventory System

This is a Java-based application that manages the product inventory of a clothing shop using a MySQL database. It allows users to perform CRUD operations (Create, Read, Update, Delete) on the product catalog.

## 🧱 Architecture

The system is built using **Layered Architecture** to promote separation of concerns, scalability, and maintainability. The layers include:

- **Presentation Layer** – Handles user interaction (e.g., console/GUI).
- **Service Layer** – Contains business logic and acts as a bridge between presentation and data access layers.
- **Repository Layer** – Handles data persistence and database operations.
- **Model Layer** – Defines data entities and domain models.

---

## 🧠 Design Patterns Used

- ✅ **Singleton Pattern**  
  Ensures a single instance of classes like `DatabaseConnection` is used throughout the application.

- ✅ **Factory Pattern**  
  Used to create service and repository objects without exposing instantiation logic.

- ✅ **Strategy Pattern (via CRUD Repository)**  
  Provides a flexible way to define different strategies for data access operations (e.g., insert, update, delete, find).

---

## 🛠️ Technologies Used

- **Java (JDK 11 or higher)**
- **MySQL**
- **JDBC**
- **Maven/Gradle (optional for dependency management)**

---

## 📌 Features

- Add new products
- View product list
- Update existing products
- Delete products
- Print results to terminal

---

## 📦 How to Run

1. Make sure MySQL is running and your database/table is set up.
2. Configure database credentials in `DBConnection.java`.
3. Compile and run `App.java`.

---

## ✍️ Author

- Sehash Dananjaya

---