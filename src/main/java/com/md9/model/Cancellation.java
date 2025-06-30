// /model/Cancellation.java
package com.md9.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.md9.model.enums.CancelledByOptions;

@Document(collection = "cancellations")
public class Cancellation {
    @Id
    private String id;

    @Indexed(unique = true)
    private String cancellationId;
    private String reservationId;
    private CancelledByOptions cancelledBy; // "User" or Admin Name
    private String reason;
    private String cancelledAt;

    // Getters

    public String getId() {
        return this.id;
    }

    public String getCancellationId() {
        return this.cancellationId;
    }

    public String getReservationId() {
        return this.reservationId;
    }

    public CancelledByOptions getCancelledBy() {
        return this.cancelledBy;
    }

    public String getReason() {
        return this.reason;
    }

    public String getCancelledAt() {
        return this.cancelledAt;
    }

    // Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setCancellationId(String cancellationId) {
        this.cancellationId = cancellationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void setCancelledBy(CancelledByOptions cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setCancelledAt(String cancelledAt) {
        this.cancelledAt = cancelledAt;
    }
}
