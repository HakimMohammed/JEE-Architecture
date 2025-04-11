package jee.mvc.tp3.services;

import jee.mvc.tp3.entities.Patient;
import org.springframework.data.domain.Page;

public interface PatientService {
    void addPatient(Patient patient);
    Page<Patient> getPatients(int page, int size);
    Page<Patient> searchPatients(String keyword, int page, int size);
    void deletePatient(String id);
    Patient editPatient(Patient patient);
    Patient getPatientById(String id);
}
