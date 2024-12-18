// /model/Reservation.java
// package com.md9.model;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import java.time.LocalDate;

// @Entity
// public class Reservation {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
   
//     private Long reservationId;
//     @Column(nullable = false)
//     private String reserverName;
//     @Column(nullable = false)
//     private String mobile;
//     @Column(nullable = false)
//     private LocalDate date;
//     @Column(nullable = false)
//     private Number timeSlotId;
//     @Column(nullable = false)
//     private String paymentMethod; // "Cash" or "Credit"
//     @Column(nullable = false, unique = true)
//     private int confirmationNo;
//     @Column(nullable = false)
//     private String status; // "Done", "Cancelled", "Pending"
package com.md9.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Column(nullable = false)
    private String reserverName;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Number timeSlotId;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false, unique = true)
    private int confirmationNo;

    @Column(nullable = false)
    private String status;

    // Getters 
    public Long getReservationId() {
        return this.reservationId;
    }

    public String getReserveeName() {
        return this.reserverName;
    }

    public String getMobileNumber() {
        return this.mobile;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Number getTimeSlot() {
        return this.timeSlotId;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public int getConfirmationNo() {
        return this.confirmationNo;
    }

    public String getStatus() {
        return this.status;
    }

    //Setters
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public void setReserveeName(String reserverName) {
        this.reserverName = reserverName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobile = mobileNumber;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimeSlot(Number timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setConfirmationNo(int confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
