package com.duoc.health_scheduler_api.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.health_scheduler_api.models.Slot;
import com.duoc.health_scheduler_api.services.SlotService;

@RestController
@RequestMapping("/api/v1/slot")
public class SlotController {

    private static final Logger logger = LoggerFactory.getLogger(SlotController.class);
    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Slot>> getSlots(@PathVariable int doctorId) {
        logger.info("Fetching slots by doctor id: {}", doctorId);
        return ResponseEntity.ok(slotService.getSlotsByDoctorId(doctorId));
    }

    @PostMapping
    public ResponseEntity<Slot> addSlot(@RequestBody Slot slot) {
        logger.info("Adding slot: {}", slot);
        return ResponseEntity.ok(slotService.addSlot(slot));
    }

    @PutMapping
    public ResponseEntity<Slot> updateSlot(@RequestBody Slot slot) {
        logger.info("Update slot: {}", slot);
        return ResponseEntity.ok(slotService.updateSlot(slot));
    }

    @DeleteMapping
    public ResponseEntity<Slot> deleteSlot(int slotId) {
        logger.info("Delete slot id: {}", slotId);
        slotService.deleteSlot(slotId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available/doctor/{doctorId}")
    public ResponseEntity<List<Slot>> getAvailableSlotsByDoctorId(@PathVariable int doctorId) {
        logger.info("Fetching available slots by doctor id: {}", doctorId);
        return ResponseEntity.ok(slotService.findAllByStatusAndDoctorId("DISPONIBLE", doctorId));
    }

}
