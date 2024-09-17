package com.duoc.health_scheduler_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.duoc.health_scheduler_api.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAllByDoctorId(int doctorId);

    List<Appointment> findAllByPatientId(int patientId);
}
