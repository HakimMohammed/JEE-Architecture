package jee.mvc.tp3.web.controllers;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;

    @GetMapping("/patients")
    public String getPatients(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Patient> patients = patientService.getPatients(page, size);
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        return "patients/index";
    }
}
