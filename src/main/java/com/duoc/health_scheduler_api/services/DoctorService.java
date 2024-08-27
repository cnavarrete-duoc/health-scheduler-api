package com.duoc.health_scheduler_api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.health_scheduler_api.models.Doctor;
import com.duoc.health_scheduler_api.models.Slots;
import com.duoc.health_scheduler_api.utils.Status;

@Service
public class DoctorService {

    private List<Doctor> doctors = new ArrayList<>();

    public DoctorService() {
        List<Slots> slots1 = new ArrayList<>();
        slots1.add(new Slots(1, new Date(), "09:00-09:30", Status.DISPONIBLE));
        slots1.add(new Slots(2, new Date(), "10:00-10:30", Status.OCUPADO));

        List<Slots> slots2 = new ArrayList<>();
        slots2.add(new Slots(3, new Date(), "11:00-11:30", Status.DISPONIBLE));
        slots2.add(new Slots(4, new Date(), "12:00-12:30", Status.DISPONIBLE));

        List<Slots> slots3 = new ArrayList<>();
        slots3.add(new Slots(5, new Date(), "13:00-13:30", Status.OCUPADO));
        slots3.add(new Slots(6, new Date(), "14:00-14:30", Status.DISPONIBLE));

        doctors.add(new Doctor(1, "Dr. Smith", "Cardiología", slots1));
        doctors.add(new Doctor(2, "Dr. Brown", "Pediatría", slots2));
        doctors.add(new Doctor(3, "Dr. Green", "Neurología", slots3));
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(int doctorId) {
        return doctors.stream().filter(d -> d.getId() == doctorId).findFirst().orElse(null);
    }

    public List<Slots> getAvailableSlotsByDoctorId(int doctorId) {
        return doctors.stream().filter(d -> d.getId() == doctorId).findFirst().get().getAvailableSlots();
    }

}
