package com.md9.controller;

import com.md9.model.Admin;
import com.md9.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ✅ Register a new admin
    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        if (adminService.findByUsername(admin.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        Admin savedAdmin = adminService.registerAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    // ✅ Fetch an admin by username
    @GetMapping("/{username}")
    public ResponseEntity<?> getAdminByUsername(@PathVariable String username) {
        Optional<Admin> adminOptional = adminService.findByUsername(username);
        if (adminOptional.isPresent()) {
            return ResponseEntity.ok(adminOptional.get()); // ✅ Return 200 OK with admin data
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found"); // ✅ Return 404 if not found
        }
    }
}