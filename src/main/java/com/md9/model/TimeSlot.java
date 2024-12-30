package com.md9.model;

// import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "timeslots")
public class TimeSlot {
    @Id
    private String id;
    private LocalTime startTime;
    private LocalTime endTime;
    // private boolean available;
    // private LocalDate blockDate;

    //constructors
    public TimeSlot(String id, LocalTime startTime, LocalTime endTime, boolean available) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        // this.available = available; Getters & Setters required
        // this.blockDate = blockDate; Getters & Setters required
    }
    public TimeSlot() {}

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
}
