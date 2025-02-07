package com.md9.model;

// import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "timeslots")
public class Timeslot {
    @Id
    private String id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String fieldId;

    //constructors
    public Timeslot(String id, LocalTime startTime, LocalTime endTime, String fieldId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fieldId = fieldId;
    }
    public Timeslot() {}

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getFieldId() {
        return this.fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }
}
