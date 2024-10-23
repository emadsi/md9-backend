// /service/ReservationService.java
package com.yourpackage.reservation.service;

import com.yourpackage.reservation.model.Reservation;
import com.yourpackage.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    public Reservation createReservation(Reservation reservation) {
        return repository.save(reservation);
    }

    public void deleteReservation(Long id) {
        repository.deleteById(id);
    }

    // Additional business logic
}
