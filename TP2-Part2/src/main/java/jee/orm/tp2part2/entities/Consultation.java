package jee.orm.tp2part2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateConsultation;
    private String reportConsultation;

    @OneToOne
    private Appointment appointment;
}
