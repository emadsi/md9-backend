package com.md9.controller;

import com.md9.model.Timeslot;
import com.md9.service.TimeslotService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/timeslots")
public class TimeslotController {

    @Autowired
    private TimeslotService timeslotService;

    //Create new Timeslot
    @PostMapping("/add")
    public ResponseEntity<String> addTimeslot(
            @RequestParam String startTime,
            @RequestParam String endTime,
            @RequestParam String fieldId) {
        timeslotService.addTimeslot(startTime, endTime, fieldId);
        return ResponseEntity.ok("Time slot added successfully.");
    }

    // Block Timeslot
    @PostMapping("/block")
    public ResponseEntity<String> blockTimeslots(
            @RequestParam(required = true) String timeslotId,
            @RequestParam String date,
            @RequestParam String fieldId) {
        timeslotService.blockTimeslot(timeslotId, date, fieldId);
        return ResponseEntity.ok("Time slots blocked successfully.");
    }

    // Fetch all time slots
    @GetMapping("/all")
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
    public void blockAllTimeslots(@RequestParam String date, @RequestParam String fieldId) {
        timeslotService.blockAllTimeslots(date, fieldId);
    }
}