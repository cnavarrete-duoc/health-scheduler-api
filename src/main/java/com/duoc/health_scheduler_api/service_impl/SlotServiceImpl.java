package com.duoc.health_scheduler_api.service_impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.health_scheduler_api.models.Slot;
import com.duoc.health_scheduler_api.repository.SlotRepository;
import com.duoc.health_scheduler_api.services.SlotService;

@Service
public class SlotServiceImpl implements SlotService {

    private SlotRepository slotRepository;

    public SlotServiceImpl(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public List<Slot> getSlotsByDoctorId(int doctorId) {
        return slotRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Slot> findAllByStatusAndDoctorId(String status, int doctorId) {
        return slotRepository.findAllByStatusAndDoctorId(status, doctorId);
    }

    @Override
    public Slot addSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    @Override
    public Slot updateSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    @Override
    public void deleteSlot(int slotId) {
        slotRepository.deleteById(slotId);
    }
}
