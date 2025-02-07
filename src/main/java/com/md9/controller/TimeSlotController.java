package com.md9.controller;

import com.md9.model.Timeslot;
import com.md9.service.TimeslotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/timeslots")
public class TimeslotController {

    @Autowired
    private TimeslotService timeslotService;

    // public TimeSlotController(TimeslotService timeslotService) {
    //     this.timeslotService = timeslotService;
    // }

    //Create new Timeslot
    @PostMapping("/add")
    public ResponseEntity<String> addTimeSlot(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String fieldId) {
        timeslotService.addTimeSlot(from, to, fieldId);
        return ResponseEntity.ok("Time slot added successfully.");
    }

    //Block Timeslot
    @PostMapping("/block")
    public ResponseEntity<String> blockTimeSlots(
            @RequestParam(required = true) String timeslotId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        timeslotService.blockTimeSlot(timeslotId, date);
        return ResponseEntity.ok("Time slots blocked successfully.");
    }

    // Fetch all time slots
    @GetMapping("/all")
    public ResponseEntity<List<Timeslot>> getAllTimeSlots() {
        List<Timeslot> timeSlots = timeslotService.getAllTimeSlots();
        return ResponseEntity.ok(timeSlots);
    }

    // Delete a time slot by ID
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteTimeSlot(@RequestParam String id) {
        timeslotService.deleteTimeSlot(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/block-all")
    public void blockAllTimeSlots(@RequestParam String date) {
        timeslotService.blockAllTimeSlots(LocalDate.parse(date));
    }

    // // Fetch available time slots
    // @GetMapping("/available")
    // public ResponseEntity<List<Timeslot>> getAvailableTimeSlots(@RequestParam boolean available) {
    //     List<Timeslot> timeSlots = timeslotService.getAvailableTimeSlots(available);
    //     return ResponseEntity.ok(timeSlots);
    // }

    // // Fetch time slots within a date range
    // @GetMapping("/range")
    // public ResponseEntity<List<Timeslot>> getTimeSlotsBetween(
    //         @RequestParam String start,
    //         @RequestParam String end
    // ) {
    //     LocalDateTime startTime = LocalDateTime.parse(start);
    //     LocalDateTime endTime = LocalDateTime.parse(end);
    //     List<Timeslot> timeSlots = timeslotService.getTimeSlotsBetween(startTime, endTime);
    //     return ResponseEntity.ok(timeSlots);
    // }

    // // Fetch a single time slot by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Timeslot> getTimeSlotById(@PathVariable String id) {
    //     return timeslotService.getTimeSlotById(id)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

    // // Create or update a time slot
    // @PostMapping
    // public ResponseEntity<Timeslot> createOrUpdateTimeSlot(@RequestBody Timeslot timeSlot) {
    //     Timeslot savedTimeSlot = timeslotService.saveTimeSlot(timeSlot);
    //     return ResponseEntity.ok(savedTimeSlot);
    // }
}