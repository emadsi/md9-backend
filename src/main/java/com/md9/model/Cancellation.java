// /model/Cancellation.java
package com.md9.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cancellations")
public class Cancellation {
    @Id
    private String cancellationId;
    private String reservationId;
    private String cancelledBy; // "User" or Admin Name

    // Getters 

    public String getCancellationId() {
        return cancellationId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }
    // Setters

    public void setCancellationId(String cancellationId) {
        this.cancellationId = cancellationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }
}
