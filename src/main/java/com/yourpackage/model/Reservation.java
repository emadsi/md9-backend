// /model/Reservation.java
package com.yourpackage.reservation.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user;
    private String field;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    // Getters and Setters
    
}
