package com.md9.repository;

import com.md9.model.Reservation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    
    // @Query("SELECT r.timeSlot FROM Reservation r WHERE r.date = :date")
    // List<String> findReservedTimeSlotsByDate(@Param("date") LocalDate date);
    @Query("SELECT r.timeslot.id FROM Reservation r WHERE r.date = :date")
    List<String> findReservedTimeslotIdsByDate(@Param("date") LocalDate date);

    @Query("{ 'timeSlotId': ?0 }")
    void updateTimeSlotIds(String oldTimeSlotId, int newTimeSlotId);
}
