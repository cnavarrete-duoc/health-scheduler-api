package com.duoc.health_scheduler_api.controllers;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.health_scheduler_api.models.Doctor;
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

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        logger.info("Adding doctor: {}", doctor);
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    @PutMapping
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor) {
        logger.info("Update doctor: {}", doctor);
        return ResponseEntity.ok(doctorService.updateDoctor(doctor));
    }

    @DeleteMapping
    public ResponseEntity<Doctor> deleteDoctor(int doctorId) {
        logger.info("Delete doctor id: {}", doctorId);
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok().build();
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
}
