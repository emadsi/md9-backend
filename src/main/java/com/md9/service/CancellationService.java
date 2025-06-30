package com.md9.service;

import com.md9.model.Cancellation;
import com.md9.model.Reservation;
import com.md9.model.enums.CancelledByOptions;
import com.md9.model.enums.ReservationStatus;
import com.md9.repository.CancellationRepository;
import com.md9.repository.DisabledTimeslotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CancellationService {

    @Autowired
    private CancellationRepository cancellationRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TimeslotService timeslotService;

    @Autowired
    private DisabledTimeslotRepository disabledTimeslotRepository;

    public Cancellation createCancellation(String reservationId, String reason, boolean isAdmin) {
        // Reservation reservation = reservationService.findById(reservationId)
        // .orElseThrow(() -> new IllegalArgumentException("Reservation not found."));
        Reservation reservation = reservationService.findByReservationId(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found."));

        if (!isAdmin && !isWithinCancellablePeriod(reservation)) {
            throw new IllegalStateException("Cancellation must be requested at least 4 hours before reservation.");
        }

        // Mark reservation as CANCELLED
        reservation.setStatus(ReservationStatus.CANCELLED);
        reservationService.save(reservation);

        // Delete from DisabledTimeslot collection
        disabledTimeslotRepository.deleteByTimeslotIdAndDate(reservation.getTimeslotId(), reservation.getDate());

        // Save cancellation record
        Cancellation cancellation = new Cancellation();
        cancellation.setCancellationId(generateUniqueCancellationId());
        cancellation.setReservationId(reservation.getConfirmationNo());
        cancellation.setReason(reason);
        cancellation.setCancelledBy(isAdmin ? CancelledByOptions.ADMIN : CancelledByOptions.USER);
        cancellation.setCancelledAt(LocalDateTime.now().toString());

        return cancellationRepository.save(cancellation);
    }

    private String generateUniqueCancellationId() {
        String cancellationId;
        do {
            cancellationId = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999));
        } while (cancellationRepository.existsByCancellationId(cancellationId));
        return cancellationId;
    }

    public boolean isWithinCancellablePeriod(Reservation reservation) {
        String dateStr = reservation.getDate();
        String startTimeStr = timeslotService.getStartTimeById(reservation.getTimeslotId());

        System.out.println("Checking cancellation timing...");
        System.out.println("Date: " + dateStr);
        System.out.println("Timeslot ID: " + reservation.getTimeslotId());
        System.out.println("Start time from timeslotService: " + startTimeStr);

        try {
            LocalDate date = LocalDate.parse(dateStr);
            LocalTime startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
            LocalDateTime reservationStart = LocalDateTime.of(date, startTime);
            return LocalDateTime.now().isBefore(reservationStart.minusHours(4));
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<Cancellation> getByReservationId(String reservationId) {
        return cancellationRepository.findByReservationId(reservationId);
    }

    public List<Cancellation> getAllCancellations() {
        return cancellationRepository.findAll();
    }
}