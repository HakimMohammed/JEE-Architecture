package jee.mvc.tp3.services;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public Page<Patient> getPatients(int page, int size) {
        return patientRepository.findAll(PageRequest.of(page, size));
    }
}
