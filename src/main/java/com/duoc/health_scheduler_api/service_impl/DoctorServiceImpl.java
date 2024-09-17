package com.duoc.health_scheduler_api.service_impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.health_scheduler_api.models.Doctor;
import com.duoc.health_scheduler_api.repository.DoctorRepository;
import com.duoc.health_scheduler_api.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(int doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id).orElse(null);
    }
}
