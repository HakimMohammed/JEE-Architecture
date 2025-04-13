# SpringBoot Application - TP3

A modern SpringBoot application with security features, database integration, and web interface.

## 🚀 Features

- **User Authentication & Authorization**
  - Secure login system
  - Role-based access control
  - User management

- **Database Integration**
  - MySQL database support
  - JPA for data persistence
  - Entity management

- **Web Interface**
  - Thymeleaf templating engine
  - Bootstrap 5.3.3 for responsive design
  - Modern and user-friendly UI

## 🛠️ Technical Stack

### Backend
- **Spring Boot 3.4.3**
- **Java 17**
- **Spring Security** - For authentication and authorization
- **Spring Data JPA** - For database operations
- **Spring Validation** - For input validation
- **Lombok** - For reducing boilerplate code

### Frontend
- **Thymeleaf** - Server-side template engine
- **Bootstrap 5.3.3** - CSS framework
- **Thymeleaf Layout Dialect** - For template layouts

### Database
- **MySQL** - Relational database management system

## 📦 Dependencies

The project uses the following main dependencies:
- `spring-boot-starter-web` - For web application support
- `spring-boot-starter-data-jpa` - For database operations
- `spring-boot-starter-thymeleaf` - For templating
- `spring-boot-starter-security` - For security features
- `spring-boot-starter-validation` - For input validation
- `mysql-connector-j` - MySQL database driver
- `lombok` - For reducing boilerplate code
- `bootstrap` - For frontend styling
- `thymeleaf-layout-dialect` - For template layouts

## 🏗️ Project Structure

```
src/main/java/jee/mvc/tp3/
├── Application.java
├── entities/         # Entity classes
├── repositories/     # Repository interfaces
├── services/        # Service layer
├── web/            # Controllers and web-related classes
└── security/       # Security configuration and related classes
```

## 📸 Screenshots

### Login Page
![Login Page](/src/main/resources/static/Login.png)

### Patient List (Admin)
![Patient Page](/src/main/resources/static/Patient%20-%20admin.png)

### Ajouter un Patient
![Create Patient Page](/src/main/resources/static/Add%20Patient.png)

### Patient List (User)
![Patient Page](/src/main/resources/static/Patient%20-%20User.png)

### Unoathorised Page
![Patient Page](/src/main/resources/static/403.png)


## 🔧 Configuration

The application requires the following configurations:
- MySQL database connection
- Security settings
- Server port configuration

## 🚀 Getting Started

1. Clone the repository
2. Configure your MySQL database
3. Update application.properties with your database credentials
4. Run the application using:
   ```bash
   mvn spring-boot:run
   ```
=