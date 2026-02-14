package com.lasya.appointment.model;

import java.time.LocalDateTime;

public record Appointment(
        Long id,
        Long patientId,
        String doctorName,
        LocalDateTime appointmentTime,
        String status
) {
}
