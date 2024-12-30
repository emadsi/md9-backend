package com.md9.controller;

import com.md9.model.DisabledTimeSlot;
import com.md9.service.DisabledTimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/disabledTimeSlots")
@CrossOrigin(origins = "http://localhost:4200")
public class DisabledTimeSlotController {
    @Autowired
    private DisabledTimeSlotService disabledTimeSlotService;

    @GetMapping("/all")
    public List<DisabledTimeSlot>  getAllDisabledTimeSlots() {
        return disabledTimeSlotService.getAllDisabledTimeSlots();
    }

    @GetMapping("/{date}")
    public List<DisabledTimeSlot> getDisabledTimeSlots(@PathVariable LocalDate date) {
        return disabledTimeSlotService.getDisabledTimeSlotsByDate(date);
    }

    @PostMapping
    public DisabledTimeSlot addDisabledTimeSlot(@RequestBody DisabledTimeSlot disabledTimeSlot) {
        return disabledTimeSlotService.addDisabledTimeSlot(disabledTimeSlot);
    }
}
