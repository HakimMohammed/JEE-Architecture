package jee.mvc.tp3.repositories;

import jee.mvc.tp3.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Page<Patient> findByNameContaining(String keyword, Pageable pageable);
    Patient findPatientById(String id);
}
