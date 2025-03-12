package jee.orm.tp2.repositories;

import jee.orm.tp2.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNameContains(String keyword);
}
