package com.md9.model;

public class CancellationRequestDto {
    private String reservationId;
    private String reason;

    // Getters & Setters
    public String getReservationId() {
        return this.reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
