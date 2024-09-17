package com.duoc.health_scheduler_api.controllers;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        logger.info("Adding appointment: {}", appointment);
        Appointment currentAppointment = appointment;
        currentAppointment.setCreateAt(new Date());
        currentAppointment.setStatus("RESERVADA");
        return ResponseEntity.ok(appointmentService.addAppointment(currentAppointment));
    }

    @PutMapping
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
        logger.info("Update appointment: {}", appointment);

        Appointment currentAppointment = appointmentService.getAppointmentById(appointment.getId());

        if (Objects.isNull(currentAppointment)) {
            return ResponseEntity.notFound().build();
        }

        currentAppointment.setDoctorName(appointment.getDoctorName());
        currentAppointment.setPatientName(appointment.getPatientName());
        currentAppointment.setAppointmentDate(appointment.getAppointmentDate());
        currentAppointment.setDoctorId(appointment.getDoctorId());
        currentAppointment.setPatientId(appointment.getPatientId());
        currentAppointment.setStatus(appointment.getStatus());

        return ResponseEntity.ok(appointmentService.updateAppointment(currentAppointment));
    }

    @DeleteMapping
    public ResponseEntity<Appointment> deleteAppointment(int appointmentId) {
        logger.info("Delete appointment id: {}", appointmentId);
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable int patientId) {
        logger.info("Fetching appointments by patient id: {}", patientId);
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable int doctorId) {
        logger.info("Fetching appointments by doctor id: {}", doctorId);
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId));
    }
}
