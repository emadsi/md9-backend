package com.md9.repository;

import com.md9.model.Reservation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    @Query("{ 'date': ?0 }")
    List<String> findReservedTimeslotIdsByDate(String date);

    Optional<Reservation> findByConfirmationNo(String confirmationNo);

    Optional<Reservation> findByReservationId(String reservationId);

    void deleteByConfirmationNo(String confirmationNo);

    boolean existsByReservationId(String reservationId);
}
