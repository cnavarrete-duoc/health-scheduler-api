package com.duoc.health_scheduler_api.models;

import java.util.Date;

import com.duoc.health_scheduler_api.utils.AppointmentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    private int id;
    private int patientId;
    private String patientName;
    private int doctorId;
    private String doctorName;
    private Date createAt;
    private Date appointmentDate;
    private AppointmentStatus status;
}
