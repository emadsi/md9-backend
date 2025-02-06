package com.md9.repository;

import com.md9.model.Timeslot;

import java.time.LocalDate;
// import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotRepository extends MongoRepository<Timeslot, String> {

    @Query("{ }")
    List<Timeslot> findAll();

    @Query("{ '_id': ?0 }")
    void blockSpecificOnDate(String timeSlotId, LocalDate date);

    @Query("{ }")
    void blockAllOnDate(LocalDate date);

    // List<Timeslot> findByAvailable(boolean available);

    // List<Timeslot> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}