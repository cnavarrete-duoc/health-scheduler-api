package com.duoc.health_scheduler_api.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.health_scheduler_api.models.Appointment;
import com.duoc.health_scheduler_api.services.AppointmentService;

@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments() {
        logger.info("Fetching all appointments");
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable int patientId) {
        logger.info("Fetching appointments by patient id: {}", patientId);
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId));
    }
}
