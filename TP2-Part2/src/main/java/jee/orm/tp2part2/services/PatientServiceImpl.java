package jee.orm.tp2part2.services;

import jakarta.transaction.Transactional;
import jee.orm.tp2part2.entities.Patient;
import jee.orm.tp2part2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        patient.setId(UUID.randomUUID().toString());
        return patientRepository.save(patient);
    }

    @Override
    public Patient findByName(String name) {
        return patientRepository.findByName(name);
    }
}
