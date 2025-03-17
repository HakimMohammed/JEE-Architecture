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
            Stream.of("Amine", "Mohamed", "Yassine", "Oussama", "Hassan").forEach(name -> {
                Patient patient = Patient.builder()
                        .name(name)
                        .email(name + "@gmail.com")
                        .isSick(false)
                        .birthDate(new Date())
                        .build();
                patientService.save(patient);
            });
        };
    }

}
