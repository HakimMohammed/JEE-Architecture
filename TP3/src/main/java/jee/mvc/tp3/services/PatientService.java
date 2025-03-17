package jee.mvc.tp3.services;

import jee.mvc.tp3.entities.Patient;
import org.springframework.data.domain.Page;

public interface PatientService {
    Patient save(Patient patient);
    Page<Patient> getPatients(int page, int size);
}
