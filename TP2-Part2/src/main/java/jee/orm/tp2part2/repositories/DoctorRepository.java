package jee.orm.tp2part2.repositories;

import jee.orm.tp2part2.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Doctor findByName(String name);
}
