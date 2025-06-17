# SmartStudent 🎓

**SmartStudent** is a Java-based Student Management System that provides a full desktop-like experience using Java Swing. It integrates with a MySQL database for secure user login and full CRUD operations on student records, including search, statistics, and export functionalities.

---

## 🚀 Features

- **Login Authentication**  
  Secure login using credentials stored in MySQL (`users` table).

- **Student Management**  
  Add, view, update, and delete student records.

- **Search Functionality**  
  Search students by name, department, or marks range.

- **Statistics Module**  
  - Count total students  
  - Identify top/lowest scorers  
  - Department-wise student count  

- **Export to CSV**  
  Export the entire student list to a CSV file for backup or analysis.

- **Swing-based GUI**  
  User-friendly interface with a dashboard and separate windows for each function.

---

## 🏗️ Tech Stack

- **Java 8+**
- **Swing (GUI)**
- **MySQL (Database)**
- **JDBC (Connectivity)**

---

## 🗂️ Project Structure

SmartStudent/
├── create/
│ ├── LoginWindow.java
│ ├── DashboardWindow.java
│ ├── StudentFormWindow.java
│ ├── SearchWindow.java
│ ├── StatisticsWindow.java
│ ├── ExportUtility.java
│ ├── DatabaseConnection.java
│ └── Main.java
├── model/
│ └── Student.java
├── dao/
│ └── StudentDAO.java
├── students.csv <-- Exported data file
├── README.md
└── .sql file for DB schema (optional)

## 🔧 Setup Instructions

1. **Install MySQL** and create a database called `studentdb`.

2. **Create Tables**:

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    rollNo VARCHAR(20),
    department VARCHAR(50),
    email VARCHAR(100),
    phone VARCHAR(15),
    subject1 INT,
    subject2 INT,
    subject3 INT,
    totalMarks INT,
    grade VARCHAR(2)
);
Insert sample user for login:

INSERT INTO users (username, password) VALUES ('admin', 'admin123');
Connect MySQL
In DatabaseConnection.java, update the URL, username, and password.

java
String url = "jdbc:mysql://localhost:3306/studentdb";
String user = "root";
String password = "your_mysql_password";
Compile and Run

Open the project in Eclipse/IntelliJ
Run Main.java

Export CSV
The exported file will be saved in your default user directory:

C:/Users/YourName/students.csv (on Windows)
~/students.csv (on Linux/Mac)

![Login page](https://github.com/user-attachments/assets/98bf605f-8ae5-4f35-b46c-6e448e529648)
![DashBoard page](https://github.com/user-attachments/assets/41280bc1-11f2-44f4-8dfc-e26a4ef99ff2)
![CRUD logic](https://github.com/user-attachments/assets/e9beac56-e0a2-4896-ad69-7425a6c7e035)
![Search page](https://github.com/user-attachments/assets/695b5436-b025-4743-88d5-609321966b7b)
![statistics page](https://github.com/user-attachments/assets/d8b764c7-5a96-4c7c-ac0d-accbf769572e)
![students_csv](https://github.com/user-attachments/assets/20ba5303-fc8f-402a-9e28-13f57c837e9d)

MIT License. Free to use and modify.




