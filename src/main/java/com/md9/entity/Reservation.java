// /model/Reservation.java
package com.md9.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

import com.md9.model.TimeSlot;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private Long reservationId;
    private String reserveeName;
    private String mobile;
    private LocalDate date;
    private TimeSlot timeSlot;
    private String paymentMethod; // "Cash" or "Credit"
    private int confirmationNo;
    private String status; // "Done", "Cancelled", "Pending"

    // Getters 
    public Long getReservationId() {
        return this.reservationId;
    }

    public String getReserveeName() {
        return this.reserveeName;
    }

    public String getMobileNumber() {
        return this.mobile;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public TimeSlot getTimeSlot() {
        return this.timeSlot;
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

    public void setReserveeName(String reserveeName) {
        this.reserveeName = reserveeName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobile = mobileNumber;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
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
