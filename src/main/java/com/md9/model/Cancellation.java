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
    private String reason;
    private String createdAt;

    // Getters 

    public String getCancellationId() {
        return cancellationId;
    }

    public String getReservationId() {
        return this.reservationId;
    }

    public String getCancelledBy() {
        return this.cancelledBy;
    }

    public String getReason() {
        return this.reason;
    }

    public String getCreatedAt() {
        return this.createdAt;
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

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
