package jee.mvc.tp3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class Patient {
    @Id
    private String id;
    @NotEmpty
    @Size(min = 5, max = 15)
    private String name;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private boolean isSick;
}
