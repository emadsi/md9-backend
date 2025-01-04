// /service/ReservationService.java
package com.md9.service;

import com.md9.model.DisabledTimeSlot;
import com.md9.model.Reservation;
import com.md9.model.TimeSlot;
import com.md9.repository.DisabledTimeSlotRepository;
import com.md9.repository.ReservationRepository;
import com.md9.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;
    private final TimeSlotRepository timeSlotRepository;
    @Autowired
    private final DisabledTimeSlotRepository disabledTimeSlotRepository;

    

    public ReservationService(ReservationRepository reservationRepository, DisabledTimeSlotRepository disabledTimeSlotRepository, TimeSlotRepository timeSlotRepository) {
        this.reservationRepository = reservationRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.disabledTimeSlotRepository = disabledTimeSlotRepository;
    }

    // public Reservation createReservation(Reservation reservation) {
    //     return reservationRepository.save(reservation);
    // }

    public Reservation createReservation(Reservation reservation) {
        DisabledTimeSlot disabledTimeSlot = new DisabledTimeSlot(disabledTimeSlotRepository.findAll().size(), reservation.getTimeSlotId(), reservation.getDate(), "reserved");
        disabledTimeSlotRepository.save(disabledTimeSlot);

        reservation.setConfirmationNo(generateConfirmationNumber().toString());
        reservation.setStatus("Pending");
        return reservationRepository.save(reservation);
    }
    
    private Long generateConfirmationNumber() {
        return (long) (Math.random() * 9000 + 1000);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // public Reservation findByConfirmationNumber(String confirmationNumber) {
    //     return reservationRepository.findByConfirmationNumber(confirmationNumber);
    // }

    public void cancelReservation(String id) {
        reservationRepository.deleteById(id);
    }

    public List<Integer> getAvailableTimeSlots(LocalDate date) {
        // Fetch all timeslots
        List<TimeSlot> allTimeslots = timeSlotRepository.findAll();

        // Fetch reserved timeslot IDs for the given date
        List<Integer> reservedTimeslotIds = reservationRepository.findReservedTimeslotIdsByDate(date);

        // Filter available timeslots
        return allTimeslots.stream()
                .filter(timeslot -> !reservedTimeslotIds.contains(timeslot.getId()))
                .map(TimeSlot::getId)
                .collect(Collectors.toList());
    }
}
