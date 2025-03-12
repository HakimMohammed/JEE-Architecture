package jee.orm.tp2;

import jee.orm.tp2.entities.Patient;
import jee.orm.tp2.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp2Application implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //- Ajouter des patients
        System.out.println("*** Ajouter des patients ***");
        patientRepository.save(Patient.builder().name("Alice").birthDate(new Date()).isSick(true).score(10).build());
        patientRepository.save(Patient.builder().name("Bob").birthDate(new Date()).isSick(false).score(20).build());
        patientRepository.save(Patient.builder().name("Charlie").birthDate(new Date()).isSick(true).score(30).build());
        patientRepository.save(Patient.builder().name("David").birthDate(new Date()).isSick(false).score(40).build());
        patientRepository.save(Patient.builder().name("Eve").birthDate(new Date()).isSick(true).score(50).build());

        //- Consulter tous les patients
        System.out.println("\n\n*** Consulter tous les patients ***");
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(System.out::println);

        //- Consulter un patient
        System.out.println("\n\n*** Consulter un patient ***");
        Patient patient = patientRepository.findById(1L).get();
        System.out.println(patient);

        //- Chercher des patients
        System.out.println("\n\n*** Chercher des patients ***");
        String keyword = "a";
        List<Patient> patientsWithKeyword = patientRepository.findByNameContains(keyword);
        patientsWithKeyword.forEach(System.out::println);

        //- Mettre à jour un patient
        System.out.println("\n\n*** Mettre à jour un patient ***");
        Patient patientToUpdate = patientRepository.findById(1L).get();
        patientToUpdate.setName("Alice Updated");
        patientRepository.save(patientToUpdate);


        //- supprimer un patient
        System.out.println("\n\n*** Supprimer un patient ***");
        patientRepository.deleteById(2L);

        //- Consulter tous les patients
        System.out.println("\n\n*** Consulter tous les patients ***");
        patients = patientRepository.findAll();
        patients.forEach(System.out::println);

    }
}
