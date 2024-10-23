// /model/Admin.java
package com.md9.model;

import javax.persistence.*;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String name;
    private String username;
    private String password;
    private String email;

    // Getters and Setters
}
