// /repository/CancellationRepository.java
package com.md9.repository;

import com.md9.model.Cancellation;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
// import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CancellationRepository extends MongoRepository<Cancellation, String> {

    @Query("{ 'cancellationId': ?0 }")
    List<Cancellation> findByCancellationId(String cancellationId);
    
    // Find cancellations by reservation ID
    List<Cancellation> findByReservationId(String reservationId);

    // Find cancellations By Who did the cancellation
    List<Cancellation> findCancelledBy(String cancelledBy);

    // Custom query to fetch cancellations within a date range
    // @Query("{ 'cancellationDate': { $gte: ?0, $lte: ?1 } }")
    // List<Cancellation> findCancellationsWithTimeslotId(Lo startDate, Date endDate);
}
