package com.md9.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.md9.model.enums.ReservationStatus;

import jakarta.validation.constraints.*;

@Document(collection = "reservations")
public class Reservation {
    @Id
    private String id;

    @Indexed(unique = true)
    private String reservationId;
    @NotBlank(message = "Reserver name is required.")
    private String reserverName;
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits.")
    private String mobile;
    @NotNull(message = "Date is required.")
    private String date;
    @NotNull(message = "Pick Time Slot.")
    private String timeslotId;
    @NotNull(message = "Pick Field ID.")
    private String fieldId;
    @NotBlank(message = "Payment method is required.")
    private String paymentMethod;
    @NotNull(message = "Confirmation number is required.")
    private String confirmationNo;
    @NotNull(message = "Status is required.")
    private ReservationStatus status;
    @NotBlank(message = "Created At is required")
    private String createdAt;

    // Getters

    public String getId() {
        return this.id;
    }

    public String getReservationId() {
        return this.reservationId;
    }

    public String getReserverName() {
        return this.reserverName;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getDate() {
        return this.date;
    }

    public String getTimeslotId() {
        return this.timeslotId;
    }

    public String getFieldId() {
        return this.fieldId;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public String getConfirmationNo() {
        return this.confirmationNo;
    }

    public ReservationStatus getStatus() {
        return this.status;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void setReserverName(String reserverName) {
        this.reserverName = reserverName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTimeslotId(String timeslotId) {
        this.timeslotId = timeslotId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
