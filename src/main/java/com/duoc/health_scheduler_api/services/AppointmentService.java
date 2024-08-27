package com.duoc.health_scheduler_api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.health_scheduler_api.models.Appointment;
import com.duoc.health_scheduler_api.utils.AppointmentStatus;

@Service
public class AppointmentService {

    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentService() {
        appointments.add(new Appointment(1, 1001, "John Doe", 2001, "Dr. Smith", new Date(), new Date(),
                AppointmentStatus.RESERVADA));
        appointments
                .add(new Appointment(2, 1002, "Jane Smith", 2002, "Dr. Brown", new Date(), new Date(),
                        AppointmentStatus.CANCELADA));
        appointments.add(
                new Appointment(3, 1003, "Robert Johnson", 2003, "Dr. Green", new Date(), new Date(),
                        AppointmentStatus.ATENDIDA));
        appointments
                .add(new Appointment(4, 1004, "Emily Davis", 2004, "Dr. Adams", new Date(), new Date(),
                        AppointmentStatus.RESERVADA));
        appointments
                .add(new Appointment(5, 1005, "Michael Clark", 2005, "Dr. Lee", new Date(), new Date(),
                        AppointmentStatus.CANCELADA));
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        return appointments.stream().filter(a -> a.getPatientId() == patientId).toList();
    }

}
