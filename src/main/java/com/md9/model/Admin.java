// // /model/Admin.java
package com.md9.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "admins")
public class Admin {
    @Id
    private String id;

    private String adminId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private boolean isSuperAdmin;

    // Getters
    public String getId() {
        return this.id;
    }
    public String getAdminId() {
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

    public String getMobile() {
        return this.mobile;
    }

    public boolean getIsSuperAdmin() {
        return this.isSuperAdmin;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setAdminId(String adminId) {
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

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setIsSuperAdmin(boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }
}