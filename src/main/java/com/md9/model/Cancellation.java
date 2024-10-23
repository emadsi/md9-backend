// /model/Cancellation.java
package com.md9.model;

import javax.persistence.*;

@Entity
public class Cancellation {
    @Id
    private Long reservationId;

    private String cancelledBy; // "User" or Admin Name

    // Getters and Setters
}
