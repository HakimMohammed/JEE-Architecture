package jee.orm.tp2part2.repositories;

import jee.orm.tp2part2.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByName(String name);
}
