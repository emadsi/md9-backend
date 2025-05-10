// /service/ReservationService.java
package com.md9.service;

import com.md9.model.DisabledTimeslot;
import com.md9.model.Reservation;
import com.md9.model.Timeslot;
import com.md9.repository.DisabledTimeslotRepository;
import com.md9.repository.ReservationRepository;
import com.md9.repository.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;

    @Autowired
    private DisabledTimeslotRepository disabledTimeslotRepository;

    public Reservation createReservation(Reservation reservation) {
        if (reservation.getConfirmationNo() == null || reservation.getConfirmationNo().isEmpty()) {
            throw new IllegalArgumentException("Confirmation number must be provided after successful payment.");
        }

        // Prevent duplicate reservation for the same timeslot on the same field/date
        boolean alreadyReserved = disabledTimeslotRepository.findByTimeslotIdAndDate(
                reservation.getTimeslotId(), reservation.getDate()).stream()
                .anyMatch(d -> d.getFieldId().equals(reservation.getFieldId()));

        if (alreadyReserved) {
            throw new IllegalStateException("This timeslot is already reserved or blocked.");
        }

        // Save DisabledTimeslot as reserved
        DisabledTimeslot disabledTimeslot = new DisabledTimeslot(
                null,
                reservation.getTimeslotId(),
                reservation.getDate(),
                "reserved",
                reservation.getFieldId());
        disabledTimeslotRepository.save(disabledTimeslot);

        // Set default values
        if (reservation.getStatus() == null || reservation.getStatus().isEmpty()) {
            reservation.setStatus("Pending");
        }
        if (reservation.getCreatedAt() == null || reservation.getCreatedAt().isEmpty()) {
            reservation.setCreatedAt(LocalDateTime.now().toString());
        }

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void cancelReservation(String id) {
        reservationRepository.deleteById(id);
    }

    public List<String> getAvailableTimeslots(String date) {
        List<Timeslot> allTimeslots = timeslotRepository.findAll();
        List<String> reservedTimeslotIds = reservationRepository.findReservedTimeslotIdsByDate(date);
        return allTimeslots.stream()
                .filter(timeSlot -> !reservedTimeslotIds.contains(timeSlot.getId()))
                .map(Timeslot::getId)
                .collect(Collectors.toList());
    }

    public Optional<Reservation> getByConfirmationNo(String confirmationNo) {
        return reservationRepository.findByConfirmationNo(confirmationNo);
    }
}