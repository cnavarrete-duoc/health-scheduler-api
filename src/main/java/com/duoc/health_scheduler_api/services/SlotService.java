package com.duoc.health_scheduler_api.services;

import java.util.List;

import com.duoc.health_scheduler_api.models.Slot;

public interface SlotService {

    public List<Slot> getSlotsByDoctorId(int doctorId);

    public Slot addSlot(Slot slot);

    public Slot updateSlot(Slot slot);

    public void deleteSlot(int slotId);

    public List<Slot> findAllByStatusAndDoctorId(String status, int doctorId);

}
