// /controller/ReservationController.java
package com.md9.controller;

import com.md9.model.Reservation;
import com.md9.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
// @CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    @Autowired
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/new")
    public Reservation createReservation(@RequestParam Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/cancel/{id}")
    public void cancelReservation(@PathVariable String id) {
        reservationService.cancelReservation(id);
    }

    @GetMapping("/available-slots")
    public ResponseEntity<List<Integer>> getAvailableTimeSlots(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Integer> availableSlots = reservationService.getAvailableTimeSlots(date);
        return ResponseEntity.ok(availableSlots);
    }
}