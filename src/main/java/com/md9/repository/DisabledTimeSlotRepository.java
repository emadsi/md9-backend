package com.md9.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.md9.model.DisabledTimeslot;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DisabledTimeslotRepository extends MongoRepository<DisabledTimeslot, String> {
    List<DisabledTimeslot> findByDate(LocalDate date);
    List<DisabledTimeslot> findByTimeSlotIdAndDate(String timeslotId, LocalDate date);
    // List<DisabledTimeslot> getAllDisabledTimeslots();
    List<DisabledTimeslot> findByReason(String reason);
}

