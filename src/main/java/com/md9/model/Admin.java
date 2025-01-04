// /model/Admin.java
package com.md9.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "admins")
public class Admin {
    @Id
    private int adminId;
    private String name;
    private String username;
    private String password;
    private String email;

    // Getters
    public int getId() {
        return this.adminId;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    // Setters
    public void setId(int adminId) {
        this.adminId = adminId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
