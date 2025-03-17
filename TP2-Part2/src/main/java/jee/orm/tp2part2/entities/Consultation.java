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
    @Id
    private String id;

    private Date dateConsultation;
    private String reportConsultation;

    @OneToOne(mappedBy = "consultation")
    private Appointment appointment;
}
