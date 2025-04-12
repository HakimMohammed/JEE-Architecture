package jee.mvc.tp3.services;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Override
    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public Page<Patient> getPatients(int page, int size) {
        return patientRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Patient getPatientById(String id) {
        return patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
    }

    @Override
    public Page<Patient> searchPatients(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return patientRepository.findByNameContaining(keyword, pageable);
    }

    @Override
    public Patient editPatient(Patient patient) {
        Patient existingPatient = patientRepository.findById(patient.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + patient.getId()));
        existingPatient.setName(patient.getName());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setBirthDate(patient.getBirthDate());
        existingPatient.setSick(patient.isSick());
        return patientRepository.save(existingPatient);
    }

    @Override
    public void deletePatient(String id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        patientRepository.delete(patient);
    }
}
