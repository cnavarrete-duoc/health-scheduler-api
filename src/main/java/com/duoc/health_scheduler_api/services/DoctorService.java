package com.duoc.health_scheduler_api.services;

import java.util.List;

import com.duoc.health_scheduler_api.models.Doctor;

public interface DoctorService {

    public List<Doctor> getAllDoctors();

    public Doctor addDoctor(Doctor doctor);

    public Doctor updateDoctor(Doctor doctor);

    public void deleteDoctor(int doctorId);

    public Doctor getDoctorById(int doctorId);
}
