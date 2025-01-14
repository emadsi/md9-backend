package com.md9.controller;

import com.md9.model.TimeSlot;
import com.md9.service.TimeSlotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
// import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/timeslots")
public class TimeSlotController {

    @Autowired
    private TimeSlotService timeSlotService;

    // public TimeSlotController(TimeSlotService timeSlotService) {
    //     this.timeSlotService = timeSlotService;
    // }

    //Create new TimeSlot
    @PostMapping("/add")
    public ResponseEntity<String> addTimeSlot(
            @RequestParam String from,
            @RequestParam String to) {
        timeSlotService.addTimeSlot(from, to);
        return ResponseEntity.ok("Time slot added successfully.");
    }

    //Block TimeSlot
    @PostMapping("/block")
    public ResponseEntity<String> blockTimeSlots(
            @RequestParam(required = true) String timeSlotId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        timeSlotService.blockTimeSlot(timeSlotId, date);
        return ResponseEntity.ok("Time slots blocked successfully.");
    }

    // Fetch all time slots
    @GetMapping("/all")
    public ResponseEntity<List<TimeSlot>> getAllTimeSlots() {
        List<TimeSlot> timeSlots = timeSlotService.getAllTimeSlots();
        return ResponseEntity.ok(timeSlots);
    }

    // Delete a time slot by ID
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteTimeSlot(@RequestParam String id) {
        timeSlotService.deleteTimeSlot(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/block-all")
    public void blockAllTimeSlots(@RequestParam String date) {
        timeSlotService.blockAllTimeSlots(LocalDate.parse(date));
    }

    // // Fetch available time slots
    // @GetMapping("/available")
    // public ResponseEntity<List<TimeSlot>> getAvailableTimeSlots(@RequestParam boolean available) {
    //     List<TimeSlot> timeSlots = timeSlotService.getAvailableTimeSlots(available);
    //     return ResponseEntity.ok(timeSlots);
    // }

    // // Fetch time slots within a date range
    // @GetMapping("/range")
    // public ResponseEntity<List<TimeSlot>> getTimeSlotsBetween(
    //         @RequestParam String start,
    //         @RequestParam String end
    // ) {
    //     LocalDateTime startTime = LocalDateTime.parse(start);
    //     LocalDateTime endTime = LocalDateTime.parse(end);
    //     List<TimeSlot> timeSlots = timeSlotService.getTimeSlotsBetween(startTime, endTime);
    //     return ResponseEntity.ok(timeSlots);
    // }

    // // Fetch a single time slot by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<TimeSlot> getTimeSlotById(@PathVariable String id) {
    //     return timeSlotService.getTimeSlotById(id)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

    // // Create or update a time slot
    // @PostMapping
    // public ResponseEntity<TimeSlot> createOrUpdateTimeSlot(@RequestBody TimeSlot timeSlot) {
    //     TimeSlot savedTimeSlot = timeSlotService.saveTimeSlot(timeSlot);
    //     return ResponseEntity.ok(savedTimeSlot);
    // }
}