package jee.mvc.tp3.web.controllers;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;

    @GetMapping("/patients")
    public String getPatients(Model model) {
        List<Patient> patients = patientService.getPatients();
        model.addAttribute("patients", patients);
        return "patients/index";
    }
}
