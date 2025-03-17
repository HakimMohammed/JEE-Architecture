package jee.mvc.tp3.web.controllers;

import jee.mvc.tp3.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;

    @GetMapping("/patients")
    public String getPatients() {
        return "patients/index";
    }
}
