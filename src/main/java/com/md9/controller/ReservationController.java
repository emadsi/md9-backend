// /controller/ReservationController.java
package com.md9.controller;

import com.md9.model.Reservation;
import com.md9.service.ReservationService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Create a reservation after payment/Obligo confirmation
     */
    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@RequestBody @Valid Reservation reservation) {
        try {
            Reservation saved = reservationService.createReservation(reservation);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Reservation creation failed.");
        }
    }

    /**
     * Get all reservations
     */
    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    /**
     * Cancel a reservation by ID (for internal use)
     */
    // @DeleteMapping("/cancel/{id}")
    // public ResponseEntity<?> cancelReservation(@PathVariable String id) {
    // reservationService.cancelReservation(id);
    // return ResponseEntity.ok("Reservation cancelled successfully.");
    // }

    /**
     * Get reservation by confirmation number (used for search/confirmation screens)
     */
    @GetMapping("/search")
    public ResponseEntity<?> getByConfirmationNumber(@RequestParam String confirmationNumber) {
        return reservationService.getByConfirmationNo(confirmationNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}