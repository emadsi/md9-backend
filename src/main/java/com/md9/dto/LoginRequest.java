package com.md9.dto;

public class LoginRequest {
    private String username;
    private String password;

    // Getter Methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setter Methods
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
