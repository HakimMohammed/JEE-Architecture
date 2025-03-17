# TP2-Part2

## Project Structure

The project is structured as follows:

- `src/main/java/jee/orm/tp2part2/`
  - `Application.java`: The main entry point of the Spring Boot application.
  - `entities/`: Contains the JPA entity classes.
    - `Appointment.java`
    - `Consultation.java`
    - `Doctor.java`
    - `Patient.java`
  - `repositories/`: Contains the Spring Data JPA repository interfaces.
    - `AppointmentRepository.java`
    - `ConsultationRepository.java`
    - `DoctorRepository.java`
    - `PatientRepository.java`
  - `services/`: Contains the service interfaces and their implementations.
    - `AppointmentService.java`
    - `AppointmentServiceImpl.java`
    - `ConsultationService.java`
    - `ConsultationServiceImpl.java`
    - `DoctorService.java`
    - `DoctorServiceImpl.java`
    - `PatientService.java`
    - `PatientServiceImpl.java`
  - `web/controllers/`: Contains the REST controllers.
    - `PatientController.java`

- `src/main/resources/`
  - `application.properties`: Configuration file for the Spring Boot application.

- `pom.xml`: Maven configuration file.

## Implementation Details

### Entities

The project uses JPA entities to represent the domain models:

- `Patient`: Represents a patient with fields for ID, name, email, and a collection of appointments.
- `Doctor`: Represents a doctor with fields for ID, name, email, speciality, and a collection of appointments.
- `Appointment`: Represents an appointment with fields for ID, date, time, doctor, patient, and consultation.
- `Consultation`: Represents a consultation with fields for ID, date, report, and appointment.

### Repositories

The project uses Spring Data JPA repositories to handle database operations:

- `PatientRepository`: Extends `JpaRepository` to provide CRUD operations for `Patient` entities.
- `DoctorRepository`: Extends `JpaRepository` to provide CRUD operations for `Doctor` entities and a custom method to find a doctor by name.
- `AppointmentRepository`: Extends `JpaRepository` to provide CRUD operations for `Appointment` entities.
- `ConsultationRepository`: Extends `JpaRepository` to provide CRUD operations for `Consultation` entities.

### Services

The project uses service interfaces and their implementations to encapsulate business logic:

- `PatientService` and `PatientServiceImpl`: Provides methods to save a patient and find a patient by name.
- `DoctorService` and `DoctorServiceImpl`: Provides methods to save a doctor and find a doctor by name.
- `AppointmentService` and `AppointmentServiceImpl`: Provides a method to save an appointment.
- `ConsultationService` and `ConsultationServiceImpl`: Provides a method to save a consultation.

### Controllers

The project uses REST controllers to handle HTTP requests:

- `PatientController`: Provides an endpoint to retrieve all patients.

### Application Configuration

- `application.properties`: Configures the Spring Boot application, including the H2 database URL and enabling the H2 console.

### Main Application

- `Application.java`: The main entry point of the Spring Boot application. It also contains a `CommandLineRunner` bean to initialize the database with sample data.

## Getting Started

To run the project, use the following command:

```bash
mvn spring-boot:run
```

The application will start on port 8080, and you can access the H2 console at `http://localhost:8080/h2-console`.
```