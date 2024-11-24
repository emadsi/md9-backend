// /repository/CancellationRepository.java
package com.md9.repository;

import com.md9.entity.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation, Long> {}
