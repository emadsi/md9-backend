package com.md9.model;

// import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "timeslots")
public class TimeSlot {
    @Id
    private int id;
    private LocalTime startTime;
    private LocalTime endTime;
    // private boolean available;
    // private LocalDate blockDate;

    //constructors
    public TimeSlot(int id, LocalTime startTime, LocalTime endTime, boolean available) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        // this.available = available; Getters & Setters required
        // this.blockDate = blockDate; Getters & Setters required
    }
    public TimeSlot() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
