package jee.mvc.tp3.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class Patient {
    @Id
    @UuidGenerator
    private String id;
    @NotEmpty
    @Size(min = 5, max = 20)
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Column(nullable = false)
    private boolean sick;
}
