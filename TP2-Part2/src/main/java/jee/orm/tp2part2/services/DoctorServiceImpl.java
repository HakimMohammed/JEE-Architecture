package jee.orm.tp2part2.services;

import jakarta.transaction.Transactional;
import jee.orm.tp2part2.entities.Doctor;
import jee.orm.tp2part2.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor save(Doctor doctor) {
        doctor.setId(UUID.randomUUID().toString());
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor findByName(String name) {
        return doctorRepository.findByName(name);
    }
}
