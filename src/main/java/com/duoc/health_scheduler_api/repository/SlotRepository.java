package com.duoc.health_scheduler_api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.health_scheduler_api.models.Slot;

public interface SlotRepository extends JpaRepository<Slot, Integer> {
    List<Slot> findAllByStatusAndDoctorId(String status, int doctorId);

    List<Slot> findAllByDoctorId(int doctorId);

}
