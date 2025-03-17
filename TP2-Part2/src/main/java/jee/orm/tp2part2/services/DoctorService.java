package jee.orm.tp2part2.services;

import jee.orm.tp2part2.entities.Doctor;

public interface DoctorService {
    Doctor save(Doctor doctor);
    Doctor findByName(String name);
}
