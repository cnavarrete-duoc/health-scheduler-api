package com.duoc.health_scheduler_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.health_scheduler_api.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

}
