package jee.orm.tp2part2.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Id
    private String id;

    private Date dateAppointment;
    private Date timeAppointment;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // To avoid infinite recursion
    private Patient patient;

    @OneToOne
    private Consultation consultation;
}