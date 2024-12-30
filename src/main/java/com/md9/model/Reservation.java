package com.md9.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "reservations")
public class Reservation {
    @Id
    private String reservationId;
    private String reserverName;
    private String mobile;
    private LocalDate date;
    private String timeSlotId;
    private String paymentMethod;
    private String confirmationNo;
    private String status;

    // Getters 
    public String getReservationId() {
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

    public String getTimeSlotId() {
        return this.timeSlotId;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public String getConfirmationNo() {
        return this.confirmationNo;
    }

    public String getStatus() {
        return this.status;
    }

    //Setters
    public void setReservationId(String reservationId) {
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

    public void setTimeSlot(String timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
