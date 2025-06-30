package com.md9.controller;

import com.md9.model.Cancellation;
import com.md9.model.CancellationRequestDto;
import com.md9.service.AdminService;
import com.md9.service.CancellationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import java.util.List;

@RestController
@RequestMapping("/api/cancellations")
public class CancellationController {

    @Autowired
    private CancellationService cancellationService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelReservation(
            @RequestBody CancellationRequestDto cancellation,
            @RequestHeader(value = "Authorization", required = false) String token) {

        String reservationId = cancellation.getReservationId();
        String reason = cancellation.getReason();

        System.out.println(">>> Cancellation Request:");
        System.out.println("reservationId = " + cancellation.getReservationId());
        System.out.println("reason = " + cancellation.getReason());

        if (reservationId == null || reason == null || reason.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Reservation number and reason are required.");
        }

        boolean isAdmin = adminService.isAdminToken(token);

        try {
            System.out.println("Looking for reservation ID: " + reservationId);
            Cancellation saved = cancellationService.createCancellation(reservationId, reason, isAdmin);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("🚫 Cancellation failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error occurred.");
        }
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<?> getCancellationByReservationId(
            @PathVariable String reservationId,
            @RequestHeader("Authorization") String token) {

        if (!adminService.isAdminToken(token)) {
            return ResponseEntity.status(403).body("Admin access required.");
        }

        return cancellationService.getByReservationId(reservationId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAllCancellations(@RequestHeader("Authorization") String token) {
        if (!adminService.isAdminToken(token)) {
            return ResponseEntity.status(403).body("Admin access required.");
        }

        return ResponseEntity.ok(cancellationService.getAllCancellations());
    }
}
