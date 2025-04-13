package jee.mvc.tp3;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.services.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner start(PatientService patientService) {
        return args -> {
            Stream.of("Amine", "Mohamed", "Yassine", "Oussama", "Hassan", "JohnQT", "JackSparrow", "MarkZuckerberg", "Bl3id", "OmarO3abd").forEach(name -> {
                Patient patient = Patient.builder()
                        .name(name)
                        .email(name + "@gmail.com")
                        .sick(false)
                        .birthDate(LocalDate.of(1990 + (int) (Math.random() * 20), 1 + (int) (Math.random() * 12), 1 + (int) (Math.random() * 28)))
                        .build();
                patientService.addPatient(patient);
            });
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
