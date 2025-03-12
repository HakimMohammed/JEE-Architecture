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
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateAppointment;
    private Date timeAppointment;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    @OneToOne
    private Consultation consultation;
}