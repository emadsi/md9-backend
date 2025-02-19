package com.md9.repository;

import com.md9.model.Reservation;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    
    // @Query("SELECT r.timeSlot FROM Reservation r WHERE r.date = :date")
    // List<String> findReservedTimeslotsByDate(@Param("date") String date);
    @Query("SELECT r.timeSlot.id FROM Reservation r WHERE r.date = :date")
    List<String> findReservedTimeslotIdsByDate(@Param("date") String date);

    @Query("{ 'timeslotId': ?0 }")
    void updateTimeslotIds(String oldTimeslotId, String newTimeslotId);
}
