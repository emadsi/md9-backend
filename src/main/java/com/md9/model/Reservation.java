// /model/Reservation.java
package com.md9.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private String reserverName;
    private String mobileNumber;
    private LocalDate date;
    private LocalTime from;
    private LocalTime to;
    private String paymentMethod; // "Cash" or "Credit"
    private String confirmationNumber;
    private String status; // "Done", "Cancelled", "Pending"

    // Getters and Setters
}
