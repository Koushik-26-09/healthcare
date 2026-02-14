package com.lasya.reminder.client;

import com.lasya.reminder.model.AppointmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "appointment-service")
public interface AppointmentClient {

    @GetMapping("/api/appointments/upcoming")
    List<AppointmentDto> getUpcomingAppointments(@RequestParam("hours") int hours);
}
