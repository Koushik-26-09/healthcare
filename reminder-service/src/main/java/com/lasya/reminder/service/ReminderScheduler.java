package com.lasya.reminder.service;

import com.lasya.reminder.client.AppointmentClient;
import com.lasya.reminder.client.PatientClient;
import com.lasya.reminder.model.AppointmentDto;
import com.lasya.reminder.model.PatientDto;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderScheduler {

    private static final Logger log = LoggerFactory.getLogger(ReminderScheduler.class);

    private final AppointmentClient appointmentClient;
    private final PatientClient patientClient;

    public ReminderScheduler(AppointmentClient appointmentClient, PatientClient patientClient) {
        this.appointmentClient = appointmentClient;
        this.patientClient = patientClient;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void sendHourlyReminders() {
        List<AppointmentDto> upcomingAppointments = appointmentClient.getUpcomingAppointments(24);
        if (upcomingAppointments.isEmpty()) {
            log.info("No upcoming appointments in next 24 hours.");
            return;
        }

        for (AppointmentDto appointment : upcomingAppointments) {
            try {
                PatientDto patient = patientClient.getPatientById(appointment.patientId());
                log.info("Reminder queued: patient='{}' email='{}' phone='{}' doctor='{}' at='{}' status='{}'",
                        patient.name(),
                        patient.email(),
                        patient.phone(),
                        appointment.doctorName(),
                        appointment.appointmentTime(),
                        appointment.status());
            } catch (FeignException ex) {
                log.warn("Unable to load patient {} for appointment {}. Cause: {}",
                        appointment.patientId(), appointment.id(), ex.getMessage());
            }
        }
    }
}
