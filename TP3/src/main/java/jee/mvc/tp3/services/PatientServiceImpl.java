package jee.mvc.tp3.services;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        patient.setId(UUID.randomUUID().toString());
        return patientRepository.save(patient);
    }
}
