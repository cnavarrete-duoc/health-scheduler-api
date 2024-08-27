package com.duoc.health_scheduler_api.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private int id;
    private String doctorName;
    private String speciality;
    private List<Slots> availableSlots;

}
