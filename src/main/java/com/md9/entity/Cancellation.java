// /model/Cancellation.java
package com.md9.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cancellationId;
    private Long reservationId;
    private String cancelledBy; // "User" or Admin Name

    // Getters 

    public Long getCancellationId() {
        return cancellationId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }
    // Setters

    public void setCancellationId(Long cancellationId) {
        this.cancellationId = cancellationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }
}
