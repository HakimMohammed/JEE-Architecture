package jee.orm.tp2part2;

import jee.orm.tp2part2.entities.Appointment;
import jee.orm.tp2part2.entities.Doctor;
import jee.orm.tp2part2.entities.Patient;
import jee.orm.tp2part2.services.AppointmentServiceImpl;
import jee.orm.tp2part2.services.DoctorServiceImpl;
import jee.orm.tp2part2.services.PatientServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner start(PatientServiceImpl patientService, DoctorServiceImpl doctorService, AppointmentServiceImpl appointmentService) {
        return args -> {
            System.out.println("Generating Patients ...");
            Stream.of("Omar", "Ali", "Hamza").forEach(name -> {
                Patient patient = new Patient();
                patient.setName(name);
                patient.setEmail(name + "@gmail.com");
                patientService.save(patient);
            });

            System.out.println("Generating Doctors ...");
            Stream.of("Ahmed","Jack","Maya").forEach(name -> {
                Doctor doctor = new Doctor();
                doctor.setName(name);
                doctor.setEmail(name + "@gmail.com");
                doctor.setSpeciality(Math.random() > 0.5 ? "Dentist" : "Cardiologist");
                doctorService.save(doctor);
            });

            System.out.println("Generating Appointments ...");
            Patient patient = patientService.findByName("Omar");
            Doctor doctor = doctorService.findByName("Ahmed");
            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            appointment.setDateAppointment(new Date());
            appointment.setTimeAppointment(new Date());
            appointmentService.save(appointment);

        };
    }
}
