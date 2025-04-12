package jee.mvc.tp3.web.controllers;

import jakarta.validation.Valid;
import jee.mvc.tp3.entities.Patient;
import jee.mvc.tp3.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private PatientService patientService;

    @GetMapping
    public String getPatients(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> patients = patientService.searchPatients(keyword, page, size);
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", patients.getTotalPages() - 1);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalPages", patients.getTotalPages());
        return "patients/index";
    }

    @GetMapping("/add")
    public String ajouterPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "patients/add-form";
    }

    @GetMapping("/edit/{id}")
    public String modifierPatient(@PathVariable String id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        return "patients/edit-form";
    }

    @PostMapping
    public String addPatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "patients/add-form";
        patientService.addPatient(patient);
        return "redirect:/patients";
    }

    @PutMapping
    public String editPatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "patients/edit-form";
        patientService.editPatient(patient);
        return "redirect:/patients";
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
