package com.duoc.health_scheduler_api.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "patientId")
    private int patientId;

    @Column(name = "patientName")
    private String patientName;

    @Column(name = "doctorId")
    private int doctorId;

    @Column(name = "doctorName")
    private String doctorName;

    @Column(name = "createAt")
    private Date createAt;

    @Column(name = "appointmentDate")
    private Date appointmentDate;

    @Column(name = "status")
    private String status;
}
