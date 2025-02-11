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

    //Create new Timeslot
    @PostMapping("/add")
    public ResponseEntity<String> addTimeslot(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String fieldId) {
        timeslotService.addTimeslot(from, to, fieldId);
        return ResponseEntity.ok("Time slot added successfully.");
    }

    // Block Timeslot
    @PostMapping("/block")
    public ResponseEntity<String> blockTimeslots(
            @RequestParam(required = true) String timeslotId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        timeslotService.blockTimeslot(timeslotId, date);
        return ResponseEntity.ok("Time slots blocked successfully.");
    }

    // Fetch all time slots
    @GetMapping()
    public ResponseEntity<List<Timeslot>> getAllTimeslots() {
        List<Timeslot> timeslots = timeslotService.getAllTimeslots();
        return ResponseEntity.ok(timeslots);
    }

    // Delete a time slot by ID
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteTimeslot(@RequestParam String id) {
        timeslotService.deleteTimeslot(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/block-all")
    public void blockAllTimeslots(@RequestParam String date) {
        timeslotService.blockAllTimeslots(LocalDate.parse(date));
    }

    // // Fetch available time slots
    // @GetMapping("/available")
    // public ResponseEntity<List<Timeslot>> getAvailableTimeslots(@RequestParam boolean available) {
    //     List<Timeslot> timeslots = timeslotService.getAvailableTimeslots(available);
    //     return ResponseEntity.ok(timeslots);
    // }

    // // Fetch time slots within a date range
    // @GetMapping("/range")
    // public ResponseEntity<List<Timeslot>> getTimeslotsBetween(
    //         @RequestParam String start,
    //         @RequestParam String end
    // ) {
    //     LocalDateTime startTime = LocalDateTime.parse(start);
    //     LocalDateTime endTime = LocalDateTime.parse(end);
    //     List<Timeslot> timeslots = timeslotService.getTimeslotsBetween(startTime, endTime);
    //     return ResponseEntity.ok(timeslots);
    // }

    // // Fetch a single time slot by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Timeslot> getTimeslotById(@PathVariable String id) {
    //     return timeslotService.getTimeslotById(id)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

    // // Create or update a time slot
    // @PostMapping
    // public ResponseEntity<Timeslot> createOrUpdateTimeslot(@RequestBody Timeslot timeslot) {
    //     Timeslot savedTimeslot = timeslotService.saveTimeslot(timeslot);
    //     return ResponseEntity.ok(savedTimeslot);
    // }
}