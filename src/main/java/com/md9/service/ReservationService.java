// /service/ReservationService.java
package com.md9.service;

import com.md9.entity.Reservation;
import com.md9.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation findByConfirmationNumber(String confirmationNumber) {
        return reservationRepository.findByConfirmationNumber(confirmationNumber);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
