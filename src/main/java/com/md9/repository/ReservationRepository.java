// /repository/ReservationRepository.java
// package com.md9.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.md9.model.Reservation;

// @Repository
// public interface ReservationRepository extends JpaRepository<Reservation, Long> {
//     Reservation findByConfirmationNumber(String confirmationNumber);
// }

package com.md9.repository;

import com.md9.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
