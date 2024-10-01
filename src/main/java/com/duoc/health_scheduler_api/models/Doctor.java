package com.duoc.health_scheduler_api.models;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor extends RepresentationModel<Doctor> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Doctor name is required")
    @NotBlank(message = "Doctor name is required")
    @Column(name = "doctorName")
    private String doctorName;

    @NotNull(message = "Speciality is required")
    @NotBlank(message = "Speciality is required")
    @Column(name = "speciality")
    private String speciality;
}
