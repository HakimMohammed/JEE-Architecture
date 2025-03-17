package jee.orm.tp2part2.services;

import jee.orm.tp2part2.entities.Patient;

public interface PatientService {
    Patient save(Patient patient);
    Patient findByName(String name);
}
