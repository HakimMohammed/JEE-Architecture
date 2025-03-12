package jee.orm.tp2part2.repositories;

import jee.orm.tp2part2.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
