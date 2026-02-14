package com.lasya.reminder.model;

public record PatientDto(
        Long id,
        String name,
        String email,
        String phone,
        String medicalHistory
) {
}
