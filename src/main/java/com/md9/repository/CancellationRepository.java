// /repository/CancellationRepository.java
package com.md9.repository;

import com.md9.model.Cancellation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancellationRepository extends MongoRepository<Cancellation, Long> {
}
