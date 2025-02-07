package com.md9.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "disabledTimeslot")
public class DisabledTimeslot {
     @Id
    private String id;
    private LocalDate date; // The date this time slot is disabled or reserved
    private String reason; // Either "blocked" or "reserved"
    private String timeslotId;

    public DisabledTimeslot(String id, String timeslotId, LocalDate date, String reason) {
        this.id = id;
        this.timeslotId = timeslotId;
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
        return timeslotId;
    }

    public void setTimeSlotId(String timeslotId) {
        this.timeslotId = timeslotId;
    }
}
