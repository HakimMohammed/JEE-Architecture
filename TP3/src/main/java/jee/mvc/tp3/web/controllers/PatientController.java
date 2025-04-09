package jee.mvc.tp3.web.controllers;

import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private PatientService patientService;

    @GetMapping
    public String getPatients(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size,
                              @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> patients = patientService.searchPatients(keyword, page, size);
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", patients.getTotalPages() - 1);
        model.addAttribute("keyword", keyword);
        return "patients/index";
    }

    @PostMapping
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients";
    }

    @PutMapping
    public String editPatient(@ModelAttribute Patient patient) {
        System.out.println(patient);
        patientService.editPatient(patient);
        return "redirect:/patients";
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
