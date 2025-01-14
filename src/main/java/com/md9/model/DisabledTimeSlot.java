package com.md9.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "disabledTimeslot")
public class DisabledTimeSlot {
     @Id
    private String id;
    private LocalDate date; // The date this time slot is disabled or reserved
    private String reason; // Either "blocked" or "reserved"
    private String timeSlotId;

    public DisabledTimeSlot(String id, String timeSlotId, LocalDate date, String reason) {
        this.id = id;
        this.timeSlotId = timeSlotId;
        this.date = date;
        this.reason = reason;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(String timeSlotId) {
        this.timeSlotId = timeSlotId;
    }
}
