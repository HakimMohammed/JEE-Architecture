package jee.mvc.tp3.services;

import jee.mvc.tp3.entities.Patient;

import java.util.List;

public interface PatientService {
    Patient save(Patient patient);
    List<Patient> getPatients();
}
