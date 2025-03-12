package jee.orm.tp2part2.repositories;

import jee.orm.tp2part2.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
