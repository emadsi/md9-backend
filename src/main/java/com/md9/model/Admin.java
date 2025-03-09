// // /model/Admin.java
package com.md9.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "admins")
public class Admin {
    @Id
    private String adminId;
    private String name;
    private String username;
    private String password;
    private String email;

    // Getters
    public String getId() {
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
    public void setId(String adminId) {
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


// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import lombok.*;

// @Document(collection = "admins")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Admin {
//     @Id
//     private String id;
//     private String name;
//     private String username;
//     private String password;
//     private String email;
// }
