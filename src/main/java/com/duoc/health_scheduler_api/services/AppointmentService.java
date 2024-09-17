package com.duoc.health_scheduler_api.services;

import java.util.List;

import com.duoc.health_scheduler_api.models.Appointment;

public interface AppointmentService {

        public List<Appointment> getAllAppointments();

        public Appointment getAppointmentById(int appointmentId);

        public Appointment addAppointment(Appointment appointment);

        public Appointment updateAppointment(Appointment appointment);

        public void deleteAppointment(int appointmentId);

        public List<Appointment> getAppointmentsByPatientId(int patientId);

        public List<Appointment> getAppointmentsByDoctorId(int doctorId);
}
