package com.lasya.patient.service;

import com.lasya.patient.model.Patient;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PatientService {

    private final Map<Long, Patient> patientStore = new ConcurrentHashMap<>();

    @PostConstruct
    void seedData() {
        patientStore.put(1L, new Patient(1L, "Aarav Nair", "aarav@example.com", "+91-9876543210", "Hypertension"));
        patientStore.put(2L, new Patient(2L, "Meera Iyer", "meera@example.com", "+91-9001122334", "Diabetes"));
    }

    public Collection<Patient> findAll() {
        return patientStore.values();
    }

    public Patient findById(Long id) {
        return patientStore.get(id);
    }

    public Patient upsert(Patient patient) {
        patientStore.put(patient.id(), patient);
        return patient;
    }
}
