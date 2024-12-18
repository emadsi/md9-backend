// package com.md9.model;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "time_slots")
// public class TimeSlot {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Number id;

//     @Column(nullable = false)
//     private String from;
//     @Column(nullable = false)
//     private String to;

//     public TimeSlot(Number id, String from, String to) {
//         this.id = id;
//         this.from = from;
//         this.to = to;
//     }
//     //Getters
//     public Number getId() {
//         return id;
//     }
//     public String getFrom() {
//         return from;
//     }
//     public String getTo() {
//         return to;
//     }
//     //Setters
//     public void setId(Number id) {
//         this.id = id;
//     }
//     public void setFrom(String from) {
//         this.from = from;
//     }
//     public void setTo(String to) {
//         this.to = to;
//     }
// }
package com.md9.model;

import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "timeslots")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
