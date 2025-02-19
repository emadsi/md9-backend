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

import java.util.List;
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
        DisabledTimeslot disabledTimeslot = new DisabledTimeslot(String.format("%d", disabledTimeslotRepository.findAll().size()), reservation.getTimeslotId(), reservation.getDate(), "reserved");
        disabledTimeslotRepository.save(disabledTimeslot);

        //confirmation Number to be replaced with CreditCard Confirmation Number
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

    public void cancelReservation(String id) {
        reservationRepository.deleteById(id);
    }

    public List<String> getAvailableTimeslots(String date) {
        // Fetch all timeslots
        List<Timeslot> allTimeslots = timeslotRepository.findAll();

        // Fetch reserved timeSlot IDs for the given date
        List<String> reservedTimeslotIds = reservationRepository.findReservedTimeslotIdsByDate(date);

        // Filter available timeslots
        return allTimeslots.stream()
                .filter(timeSlot -> !reservedTimeslotIds.contains(timeSlot.getId()))
                .map(Timeslot::getId)
                .collect(Collectors.toList());
    }
}
