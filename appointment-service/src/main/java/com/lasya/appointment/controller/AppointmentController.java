package com.lasya.appointment.controller;

import com.lasya.appointment.model.Appointment;
import com.lasya.appointment.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public Collection<Appointment> getAllAppointments() {
        return appointmentService.findAll();
    }

    @GetMapping("/upcoming")
    public Collection<Appointment> getUpcomingAppointments(@RequestParam(defaultValue = "24") int hours) {
        return appointmentService.findUpcomingWithinHours(hours);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> createOrUpdate(@PathVariable Long id, @RequestBody Appointment appointment) {
        if (!id.equals(appointment.id())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(appointmentService.upsert(appointment));
    }
}
