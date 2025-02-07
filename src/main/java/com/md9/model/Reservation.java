package com.md9.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;


@Document(collection = "reservations")
public class Reservation {
    @Id
    private String reservationId;
    @NotBlank(message = "Reserver name is required.")
    private String reserverName;
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
    private String mobile;
    @NotNull(message = "Date is required.")
    private LocalDate date;
    @NotNull(message = "Pick Time Slot.")
    private String timeslotId;
    @NotBlank(message = "Payment method is required.")
    private String paymentMethod;
    @NotNull(message = "Confirmation number is required.")
    private String confirmationNo;
    @NotBlank(message = "Status is required.")
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
        return this.timeslotId;
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

    public void setTimeSlot(String timeslotId) {
        this.timeslotId = timeslotId;
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
