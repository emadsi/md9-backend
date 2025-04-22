package com.md9.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "disabledTimeslot")
public class DisabledTimeslot {
     @Id
    private String id;
    private String date; // The date this time slot is disabled or reserved
    private String fieldId;
    private String reason; // Either "blocked" or "reserved"
    private String timeslotId;

    public DisabledTimeslot(String id, String timeslotId, String date, String reason, String fieldId) {
        this.id = id;
        this.timeslotId = timeslotId;
        this.date = date;
        this.fieldId = fieldId;
        this.reason = reason;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(String timeslotId) {
        this.timeslotId = timeslotId;
    }
}
