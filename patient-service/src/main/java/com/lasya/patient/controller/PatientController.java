package com.lasya.patient.controller;

import com.lasya.patient.model.Patient;
import com.lasya.patient.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public Collection<Patient> getPatients() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        Patient patient = patientService.findById(id);
        return patient == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> createOrUpdate(@PathVariable Long id, @RequestBody Patient patient) {
        if (!id.equals(patient.id())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(patientService.upsert(patient));
    }
}
