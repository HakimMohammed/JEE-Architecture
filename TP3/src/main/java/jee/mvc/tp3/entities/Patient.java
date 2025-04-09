package jee.mvc.tp3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class Patient {
    @Id
    private String id;

    private String name;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private boolean isSick;
}
