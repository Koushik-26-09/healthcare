package com.lasya.reminder.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private final ReminderScheduler reminderScheduler;

    public ReminderController(ReminderScheduler reminderScheduler) {
        this.reminderScheduler = reminderScheduler;
    }

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerNow() {
        reminderScheduler.sendHourlyReminders();
        return ResponseEntity.ok("Reminder job executed.");
    }
}
