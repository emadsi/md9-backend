// /service/ReservationService.java
package com.md9.service;

import com.md9.model.DisabledTimeslot;
import com.md9.model.Reservation;
import com.md9.model.enums.ReservationStatus;
import com.md9.repository.DisabledTimeslotRepository;
import com.md9.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // @Autowired
    // private TimeslotRepository timeslotRepository;

    @Autowired
    private DisabledTimeslotRepository disabledTimeslotRepository;

    public Reservation createReservation(Reservation reservation) {
        if (reservation.getConfirmationNo() == null || reservation.getConfirmationNo().isEmpty()) {
            throw new IllegalArgumentException("Confirmation number must be provided after successful payment.");
        }

        boolean alreadyReserved = disabledTimeslotRepository.findByTimeslotIdAndDate(
                reservation.getTimeslotId(), reservation.getDate()).stream()
                .anyMatch(d -> d.getFieldId().equals(reservation.getFieldId()));

        if (alreadyReserved) {
            throw new IllegalStateException("This timeslot is already reserved or blocked.");
        }

        DisabledTimeslot disabledTimeslot = new DisabledTimeslot(
                null,
                reservation.getTimeslotId(),
                reservation.getDate(),
                "reserved",
                reservation.getFieldId());
        disabledTimeslotRepository.save(disabledTimeslot);

        // ✅ Set defaults
        if (reservation.getStatus() == null) {
            reservation.setStatus(ReservationStatus.PENDING);
        }

        if (reservation.getCreatedAt() == null || reservation.getCreatedAt().isEmpty()) {
            reservation.setCreatedAt(LocalDateTime.now().toString());
        }

        // ✅ Generate unique random numeric reservationId
        reservation.setReservationId(generateUniqueReservationId());

        return reservationRepository.save(reservation);
    }

    private String generateUniqueReservationId() {
        String reservationId;
        do {
            reservationId = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999));
        } while (reservationRepository.existsByReservationId(reservationId));
        return reservationId;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getByConfirmationNo(String confirmationNo) {
        return reservationRepository.findByConfirmationNo(confirmationNo);
    }

    public Optional<Reservation> findById(String reservationId) {
        // return reservationRepository.findById(reservationId);
        System.out.println("Searching for reservation with ID: " + reservationId);
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        System.out.println("Found reservation? " + reservation.isPresent());
        return reservation;
    }

    public Optional<Reservation> findByReservationId(String reservationId) {
        return reservationRepository.findByReservationId(reservationId);
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}