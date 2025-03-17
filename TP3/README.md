# Activité Pratique N°3 - Spring MVC, Spring Data JPA Thymeleaf

This project demonstrates a typical Spring Boot MVC application. It uses Spring MVC for the web layer, Spring Data JPA for data access, and Thymeleaf as the template engine.

## Project Structure

- **Controller (MVC)**: Handles incoming HTTP requests and manages responses.
    - **PatientController**
```java
package jee.mvc.tp3.web.controllers;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
private PatientService patientService;

    @GetMapping("/patients")
    public String getPatients(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size,
                              @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> patients = patientService.searchPatients(keyword, page, size);
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", patients.getTotalPages() - 1);
        model.addAttribute("keyword", keyword);
        return "patients/index";
    }

    @DeleteMapping("/patients/{id}")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
```

- **Service**: Contains business logic and coordinates between the controller and repository layers.
    -**PatientService**
```java
package jee.mvc.tp3.services;

import jee.mvc.tp3.entities.Patient;
import org.springframework.data.domain.Page;

public interface PatientService {
    Patient save(Patient patient);
    Page<Patient> getPatients(int page, int size);
    Page<Patient> searchPatients(String keyword, int page, int size);
    Patient deletePatient(String id);
}
```
    -**PatientServiceImpl** 
```java
package jee.mvc.tp3.services;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        patient.setId(UUID.randomUUID().toString());
        return patientRepository.save(patient);
    }

    @Override
    public Page<Patient> getPatients(int page, int size) {
        return patientRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Patient> searchPatients(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return patientRepository.findByNameContaining(keyword, pageable);
    }

    @Override
    public Patient deletePatient(String id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        patientRepository.delete(patient);
        return patient;
    }
}
```

- **Repository**: Provides data access operations using Spring Data JPA.
    - **PatientRepository**
```java
package jee.mvc.tp3.repositories;

import jee.mvc.tp3.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Page<Patient> findByNameContaining(String keyword, Pageable pageable);
}
```

- **Entity**: Represents the data model mapped to the database tables.
    - **Patient**
```java
package jee.mvc.tp3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class Patient {
    @Id
    private String id;

    private String name;
    private String email;
    private Date birthDate;
    private boolean isSick;
}
```

- **Application Class**: Contains the main method to bootstrap the Spring Boot application.
    - Example: `src/main/java/jee/mvc/tp3/Application.java`
```java
package jee.mvc.tp3;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.services.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner start(PatientService patientService) {
        return args -> {
            Stream.of("Amine", "Mohamed", "Yassine", "Oussama", "Hassan", "John", "Jack", "Mark", "Bl3id", "Omar").forEach(name -> {
                Patient patient = Patient.builder()
                        .name(name)
                        .email(name + "@gmail.com")
                        .isSick(Math.random() > 0.5)
                        .birthDate(new Date())
                        .build();
                patientService.save(patient);
            });
        };
    }

}
```
- **View**: Contains the HTML templates using Thymeleaf.
    - **index.html**
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Patients</title>
    <link rel="stylesheet" href="webjars/bootstrap/5.3.3/css/bootstrap.min.css">
</head>
<body class="m-0 p-0">

<div class="">
    <div class="card">
        <div class="card-header">Liste des patients</div>
        <div class="card-body">

            <form class="input-group w-25 mb-5 ms-auto" method="get" th:action="@{patients}">
                <input type="search" name="keyword" class="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" th:value="${keyword}"/>
                <button type="submit" class="btn btn-outline-primary" data-mdb-ripple-init>search</button>
            </form>

            <table class="table">
                <thead>
                <tr>
                    <th>Identifier</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Birth Date</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="patient : ${patients}">
                    <td th:text="${patient.id}"></td>
                    <td th:text="${patient.name}"></td>
                    <td th:text="${patient.email}"></td>
                    <td th:text="${patient.birthDate}"></td>
                    <td th:text="${patient.isSick} ? 'Sick' : 'Healthy'"></td>
<!--                    <td>-->
<!--                        <a th:href="@{delete(id=${patient.id})}" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this patient ?')">Delete</a>-->
<!--                    </td>-->
                    <td>
                        <form th:action="@{/patients/{id}(id=${patient.id})}" method="post" onsubmit="return confirm('Are you sure you want to delete this patient?');">
                            <input type="hidden" name="_method" value="DELETE">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>

            <ul class="pagination w-25 mt-5 ms-auto ">
                <li th:class="${currentPage == 0 ? 'page-item disabled' : 'page-item'}">
                    <a th:class="${currentPage == 0 ? 'page-link disabled' : 'page-link'}" th:href="@{/patients(page=${currentPage - 1}, keyword=${keyword})}" th:text="Previous"></a>
                </li>
                <li th:each="page,status : ${pages}">
                    <a th:class="${currentPage == status.index ? 'page-link active' : 'page-link'}" th:href="@{/patients(page=${status.index}, keyword=${keyword})}" th:text="${status.index}"></a>
                </li>
                <li th:class="${currentPage == lastPage ? 'page-item disabled' : 'page-item'}">
                    <a th:class="${currentPage == lastPage ? 'page-link disabled' : 'page-link'}" th:href="@{/patients(page=${currentPage + 1}, keyword=${keyword})}" th:text="Next"></a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
```



## Technologies Used

- Java 17
- Spring Boot 3.4.3
- Spring Data JPA
- Thymeleaf
- Maven
- H2/MySQL database

## Running the Project

1. Clone the repository.
2. Configure the database connection in `src/main/resources/application.properties`.
3. Build the project with Maven:
   ```bash
   mvn clean install