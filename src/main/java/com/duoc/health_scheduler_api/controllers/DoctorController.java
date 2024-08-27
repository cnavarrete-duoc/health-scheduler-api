package com.duoc.health_scheduler_api.controllers;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.health_scheduler_api.models.Doctor;
import com.duoc.health_scheduler_api.models.Slots;
import com.duoc.health_scheduler_api.services.DoctorService;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctors() {
        logger.info("Received request to get all appointments");
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int doctorId) {
        logger.info("Fetching doctor by id: {}", doctorId);

        Doctor doctor = doctorService.getDoctorById(doctorId);

        if (Objects.isNull(doctor)) {
            logger.error("Doctor with id {} not found", doctorId);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/slots/{doctorId}")
    public ResponseEntity<List<Slots>> getAvailableSlotsByDoctorId(
            @PathVariable int doctorId) {
        logger.info("Fetching available slots by doctor id: {}", doctorId);

        return ResponseEntity.ok(doctorService.getAvailableSlotsByDoctorId(doctorId));
    }

}
