// /model/Cancellation.java
package com.md9.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cancellations")
public class Cancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cancellationId;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Long reservationId;

    @Column(nullable = false)
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
