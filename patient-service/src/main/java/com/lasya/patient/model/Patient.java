package com.lasya.patient.model;

public record Patient(
        Long id,
        String name,
        String email,
        String phone,
        String medicalHistory
) {
}
