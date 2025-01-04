// /model/Cancellation.java
package com.md9.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cancellations")
public class Cancellation {
    @Id
    private int cancellationId;
    private Long reservationId;
    private String cancelledBy; // "User" or Admin Name

    // Getters 

    public int getCancellationId() {
        return cancellationId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }
    // Setters

    public void setCancellationId(int cancellationId) {
        this.cancellationId = cancellationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }
}
