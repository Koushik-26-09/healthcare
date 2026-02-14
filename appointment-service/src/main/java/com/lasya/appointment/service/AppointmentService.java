package com.lasya.appointment.service;

import com.lasya.appointment.model.Appointment;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final Map<Long, Appointment> appointmentStore = new ConcurrentHashMap<>();

    @PostConstruct
    void seedData() {
        appointmentStore.put(1L, new Appointment(1L, 1L, "Dr. Rao", LocalDateTime.now().plusHours(5), "BOOKED"));
        appointmentStore.put(2L, new Appointment(2L, 2L, "Dr. Kapoor", LocalDateTime.now().plusHours(28), "BOOKED"));
        appointmentStore.put(3L, new Appointment(3L, 1L, "Dr. Menon", LocalDateTime.now().plusHours(20), "RESCHEDULED"));
    }

    public Collection<Appointment> findAll() {
        return appointmentStore.values().stream()
                .sorted(Comparator.comparing(Appointment::appointmentTime))
                .collect(Collectors.toList());
    }

    public Appointment upsert(Appointment appointment) {
        appointmentStore.put(appointment.id(), appointment);
        return appointment;
    }

    public Collection<Appointment> findUpcomingWithinHours(int hours) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cutoff = now.plusHours(hours);

        return appointmentStore.values().stream()
                .filter(appointment -> !appointment.appointmentTime().isBefore(now))
                .filter(appointment -> !appointment.appointmentTime().isAfter(cutoff))
                .filter(appointment -> "BOOKED".equalsIgnoreCase(appointment.status()) || "RESCHEDULED".equalsIgnoreCase(appointment.status()))
                .sorted(Comparator.comparing(Appointment::appointmentTime))
                .collect(Collectors.toList());
    }
}
