package com.duoc.health_scheduler_api.controllers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
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
import com.duoc.health_scheduler_api.utils.Keys;
import com.duoc.health_scheduler_api.utils.Messages;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class DoctorController {

        private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);
        private final DoctorService doctorService;

        public DoctorController(DoctorService doctorService) {
                this.doctorService = doctorService;
        }

        @GetMapping("/doctors")
        public ResponseEntity<CollectionModel<EntityModel<Doctor>>> getDoctors() {
                logger.info("GET /api/v1/doctor");
                logger.info("Received request to get all appointments");

                List<Doctor> doctors = doctorService.getAllDoctors();
                List<EntityModel<Doctor>> doctorModels = doctors.stream()
                                .map(doctor -> EntityModel.of(doctor,
                                                WebMvcLinkBuilder.linkTo(
                                                                WebMvcLinkBuilder.methodOn(this.getClass())
                                                                                .getDoctorById(doctor.getId()))
                                                                .withSelfRel(),
                                                WebMvcLinkBuilder
                                                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                                                                                .updateDoctor(doctor.getId(),
                                                                                                doctor))
                                                                .withRel(Keys.UPDATE),
                                                WebMvcLinkBuilder
                                                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                                                                                .deleteDoctor(doctor.getId()))
                                                                .withRel(Keys.DELETE)))
                                .collect(Collectors.toList());

                CollectionModel<EntityModel<Doctor>> collectionModel = CollectionModel.of(doctorModels,
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getDoctors())
                                                .withSelfRel());

                return ResponseEntity.ok(collectionModel);
        }

        @PostMapping(path = "/doctor", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<EntityModel<Doctor>> addDoctor(@Valid @RequestBody Doctor doctor) {
                logger.info("POST /api/v1/doctor");
                logger.info("Adding doctor: {}", doctor);

                Doctor newDoctor = doctorService.addDoctor(doctor);

                EntityModel<Doctor> doctorModel = EntityModel.of(newDoctor,
                                WebMvcLinkBuilder.linkTo(
                                                WebMvcLinkBuilder.methodOn(this.getClass())
                                                                .getDoctorById(newDoctor.getId()))
                                                .withSelfRel(),
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getDoctors())
                                                .withRel(Keys.DOCTORS));

                return ResponseEntity.created(
                                WebMvcLinkBuilder.linkTo(
                                                WebMvcLinkBuilder.methodOn(this.getClass())
                                                                .getDoctorById(newDoctor.getId()))
                                                .toUri())
                                .body(doctorModel);
        }

        @PutMapping("/doctor/{doctorId}")
        public ResponseEntity<EntityModel<Doctor>> updateDoctor(@PathVariable int doctorId,
                        @Valid @RequestBody Doctor doctor) {
                logger.info("PUT /api/v1/doctor/{}", doctorId);
                logger.info("Update doctor: {}", doctor);

                Doctor currentDoctor = doctorService.getDoctorById(doctorId);

                if (Objects.isNull(currentDoctor)) {
                        logger.error(Messages.DOCTOR_NOT_FOUND, doctorId);
                        return ResponseEntity.notFound().build();
                }

                currentDoctor.setDoctorName(doctor.getDoctorName());
                currentDoctor.setSpeciality(doctor.getSpeciality());

                Doctor updatedDoctor = doctorService.updateDoctor(currentDoctor);

                EntityModel<Doctor> doctorModel = EntityModel.of(updatedDoctor,
                                WebMvcLinkBuilder.linkTo(
                                                WebMvcLinkBuilder.methodOn(this.getClass())
                                                                .getDoctorById(updatedDoctor.getId()))
                                                .withSelfRel(),
                                WebMvcLinkBuilder
                                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateDoctor(
                                                                updatedDoctor.getId(),
                                                                doctor))
                                                .withRel(Keys.UPDATE),
                                WebMvcLinkBuilder
                                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                                                                .deleteDoctor(updatedDoctor.getId()))
                                                .withRel(Keys.DELETE),
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getDoctors())
                                                .withRel(Keys.DOCTORS));

                return ResponseEntity.ok(doctorModel);
        }

        @GetMapping("/doctor/{doctorId}")
        public ResponseEntity<EntityModel<Doctor>> getDoctorById(@PathVariable int doctorId) {
                logger.info("GET /api/v1/doctor/{}", doctorId);
                logger.info("Fetching doctor by id: {}", doctorId);

                Doctor doctor = doctorService.getDoctorById(doctorId);

                if (Objects.isNull(doctor)) {
                        logger.error("Doctor not found with ID {}", doctorId);
                        return ResponseEntity.notFound().build();
                }

                EntityModel<Doctor> doctorModel = EntityModel.of(doctor,
                                WebMvcLinkBuilder.linkTo(
                                                WebMvcLinkBuilder.methodOn(this.getClass()).getDoctorById(doctorId))
                                                .withSelfRel(),
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getDoctors())
                                                .withRel(Keys.DOCTORS),
                                WebMvcLinkBuilder
                                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                                                                .updateDoctor(doctorId, doctor))
                                                .withRel(Keys.UPDATE),
                                WebMvcLinkBuilder
                                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                                                                .deleteDoctor(doctorId))
                                                .withRel(Keys.DELETE));

                return ResponseEntity.ok(doctorModel);
        }

        @DeleteMapping("/doctor/{doctorId}")
        public ResponseEntity<Void> deleteDoctor(@PathVariable int doctorId) {
                logger.info("DELETE /api/v1/doctor/{}", doctorId);
                logger.info("Delete doctor id: {}", doctorId);

                Doctor doctor = doctorService.getDoctorById(doctorId);

                if (doctor == null) {
                        logger.error("Doctor not found with ID {}", doctorId);
                        return ResponseEntity.notFound().build();
                }

                doctorService.deleteDoctor(doctorId);

                return ResponseEntity.noContent().build();
        }
}
