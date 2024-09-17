package com.duoc.health_scheduler_api.service_impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.health_scheduler_api.models.Appointment;
import com.duoc.health_scheduler_api.repository.AppointmentRepository;
import com.duoc.health_scheduler_api.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(int appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        return appointmentRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return appointmentRepository.findAllByPatientId(patientId);
    }
}
