package com.md9.controller;

import com.md9.model.DisabledTimeSlot;
import com.md9.service.DisabledTimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disabledTimeSlots")
public class DisabledTimeSlotController {
    @Autowired
    private DisabledTimeSlotService disabledTimeSlotService;

    @GetMapping
    public List<DisabledTimeSlot> getAllDisabledTimeSlots() {
        return disabledTimeSlotService.getAllDisabledTimeSlots();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisabledTimeSlot> getDisabledTimeSlotById(@PathVariable String id) {
        Optional<DisabledTimeSlot> timeSlot = disabledTimeSlotService.getDisabledTimeSlotById(id);
        return timeSlot.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{date}")
    public List<DisabledTimeSlot> getDisabledTimeSlots(@PathVariable LocalDate date) {
        return disabledTimeSlotService.getDisabledTimeSlotsByDate(date);
    }

    @PostMapping
    public DisabledTimeSlot addDisabledTimeSlot(@RequestBody DisabledTimeSlot disabledTimeSlot) {
        return disabledTimeSlotService.addDisabledTimeSlot(disabledTimeSlot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisabledTimeSlot> updateDisabledTimeSlot(
            @PathVariable String id, @RequestBody DisabledTimeSlot updatedTimeSlot) {
        try {
            return ResponseEntity.ok(disabledTimeSlotService.updateDisabledTimeSlot(id, updatedTimeSlot));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisabledTimeSlot(@PathVariable String id) {
        disabledTimeSlotService.deleteDisabledTimeSlot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reason/{reason}")
    public List<DisabledTimeSlot> getTimeSlotsByReason(@PathVariable String reason) {
        return disabledTimeSlotService.getTimeSlotsByReason(reason);
    }
}
