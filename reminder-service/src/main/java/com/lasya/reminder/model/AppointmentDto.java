package com.lasya.reminder.model;

import java.time.LocalDateTime;

public record AppointmentDto(
        Long id,
        Long patientId,
        String doctorName,
        LocalDateTime appointmentTime,
        String status
) {
}
