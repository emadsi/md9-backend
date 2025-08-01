package com.md9.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.md9.model.DisabledTimeslot;
import java.util.List;

@Repository
public interface DisabledTimeslotRepository extends MongoRepository<DisabledTimeslot, String> {
    List<DisabledTimeslot> findByDate(String date);

    List<DisabledTimeslot> findByTimeslotIdAndDate(String timeslotId, String date);

    List<DisabledTimeslot> findAll();

    List<DisabledTimeslot> findByReason(String reason);

    void deleteByTimeslotIdAndDate(String timeslotId, String date);
}
