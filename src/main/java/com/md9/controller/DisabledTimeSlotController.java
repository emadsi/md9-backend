package com.md9.controller;

import com.md9.model.DisabledTimeslot;
import com.md9.service.DisabledTimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disabledTimeslots")
public class DisabledTimeslotController {
    @Autowired
    private DisabledTimeslotService disabledTimeslotService;

    @GetMapping
    public List<DisabledTimeslot> getAllDisabledTimeslots() {
        return disabledTimeslotService.getAllDisabledTimeslots();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisabledTimeslot> getDisabledTimeslotById(@PathVariable String id) {
        Optional<DisabledTimeslot> timeSlot = disabledTimeslotService.getDisabledTimeslotById(id);
        return timeSlot.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{date}")
    public List<DisabledTimeslot> getDisabledTimeslots(@PathVariable LocalDate date) {
        return disabledTimeslotService.getDisabledTimeslotsByDate(date);
    }

    @PostMapping
    public DisabledTimeslot addDisabledTimeslot(@RequestBody DisabledTimeslot disabledTimeslot) {
        return disabledTimeslotService.addDisabledTimeslot(disabledTimeslot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisabledTimeslot> updateDisabledTimeslot(
            @PathVariable String id, @RequestBody DisabledTimeslot updatedTimeSlot) {
        try {
            return ResponseEntity.ok(disabledTimeslotService.updateDisabledTimeslot(id, updatedTimeSlot));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisabledTimeslot(@PathVariable String id) {
        disabledTimeslotService.deleteDisabledTimeslot(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reason/{reason}")
    public List<DisabledTimeslot> getTimeSlotsByReason(@PathVariable String reason) {
        return disabledTimeslotService.getTimeSlotsByReason(reason);
    }
}
