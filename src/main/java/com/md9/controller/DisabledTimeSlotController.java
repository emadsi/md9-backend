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

    @GetMapping("/all")
    public List<DisabledTimeslot> getAllDisabledTimeslots() {
        return disabledTimeslotService.getAllDisabledTimeslots();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisabledTimeslot> getDisabledTimeslotById(@PathVariable String id) {
        Optional<DisabledTimeslot> timeslot = disabledTimeslotService.getDisabledTimeslotById(id);
        return timeslot.map(ResponseEntity::ok)
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
            @PathVariable String id, @RequestBody DisabledTimeslot updatedTimeslot) {
        try {
            return ResponseEntity.ok(disabledTimeslotService.updateDisabledTimeslot(id, updatedTimeslot));
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
    public List<DisabledTimeslot> getTimeslotsByReason(@PathVariable String reason) {
        return disabledTimeslotService.getTimeslotsByReason(reason);
    }
}
