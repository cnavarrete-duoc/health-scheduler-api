package com.duoc.health_scheduler_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "slot")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "dateSlot")
    private String date;

    @NotBlank(message = "Doctor id is required")
    @Column(name = "doctorId")
    private int doctorId;

    @NotBlank(message = "Doctor name is required")
    @Column(name = "doctorName")
    private String doctorName;

    @Column(name = "timeSlot")
    private String time;

    @Column(name = "status")
    private String status;
}
