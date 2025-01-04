package com.md9.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.md9.model.DisabledTimeSlot;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DisabledTimeSlotRepository extends MongoRepository<DisabledTimeSlot, String> {
    List<DisabledTimeSlot> findByDate(LocalDate date);
    List<DisabledTimeSlot> findByTimeSlotIdAndDate(int timeSlotId, LocalDate date);
}

