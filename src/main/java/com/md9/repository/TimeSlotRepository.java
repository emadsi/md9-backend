package com.md9.repository;

import com.md9.model.TimeSlot;

import java.time.LocalDate;
// import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends MongoRepository<TimeSlot, String> {

    @Query("{ }")
    List<TimeSlot> findAll();

    @Query("{ '_id': ?0 }")
    void blockSpecificOnDate(int timeSlotId, LocalDate date);

    @Query("{ }")
    void blockAllOnDate(LocalDate date);

    // List<TimeSlot> findByAvailable(boolean available);

    // List<TimeSlot> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}