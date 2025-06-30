package com.md9.repository;

import com.md9.model.Timeslot;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeslotRepository extends MongoRepository<Timeslot, String> {

    @Query("{ }")
    List<Timeslot> findAll();

    @Query("{ 'id': ?0 }")
    void blockSpecificOnDate(String timeslotId, String date);

    @Query("{ }")
    void blockAllOnDate(String date);

    @Query("{ 'timeslotId': ?0 }")
    void updateTimeslotIds(String oldTimeslotId, String newTimeslotId);
    
    Optional<Timeslot> findByTimeslotId(String timeslotId); 
}