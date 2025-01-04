package com.md9.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "disabledTimeslot")
public class DisabledTimeSlot {
     @Id
    private int id;
    private LocalDate date; // The date this time slot is disabled/reserved
    private String reason; // Either "blocked" or "reserved"
    private int timeSlotId;

    public DisabledTimeSlot(int id, int timeSlotId, LocalDate date, String reason) {
        this.id = id;
        this.timeSlotId = timeSlotId;
        this.date = date;
        this.reason = reason;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int timeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }
}
