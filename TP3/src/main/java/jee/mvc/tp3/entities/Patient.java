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
