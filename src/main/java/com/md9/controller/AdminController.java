package com.md9.controller;

import com.md9.model.Admin;
import com.md9.service.AdminService;
import com.md9.service.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    public AdminController(AdminService adminService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }

    // ✅ Register a new admin
    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin, Authentication authentication) {
        // String requesterUsername = authentication.getName();
        // return adminService.registerAdmin(admin, requesterUsername);

        System.out.println("[AdminController] 🔐 Register Admin endpoint called");
        if (authentication == null) {
            System.out.println("[AdminController] ❌ Authentication object is null");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication failed: No authentication object");
        }

        String requesterUsername = authentication.getName();
        System.out.println("[AdminController] 👤 Requester Username: " + requesterUsername);
        System.out.println("[AdminController] 📦 New Admin Data: " + admin.getUsername() + ", SuperAdmin? " + admin.getIsSuperAdmin());

        return adminService.registerAdmin(admin, requesterUsername);
    }

    // ✅ Edit Admin Profile
    @PutMapping("/update")
    public ResponseEntity<?> updateAdmin(@RequestHeader("Authorization") String token, @RequestBody Admin adminUpdate) {
        // String username = jwtUtil.extractUsername(token.substring(7));
        // return adminService.updateAdmin(username, adminUpdate);

        System.out.println("[AdminController] ✏️ Update Admin called");
        String username = jwtUtil.extractUsername(token.substring(7));
        System.out.println("[AdminController] 👤 Username from token: " + username);
        return adminService.updateAdmin(username, adminUpdate);
    }

    // ✅ Change Password (Admin)
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> passwordData) {
        // String username = jwtUtil.extractUsername(token.substring(7));
        // return adminService.changePassword(username, passwordData);

        System.out.println("[AdminController] 🔑 Change Password called");
        String username = jwtUtil.extractUsername(token.substring(7));
        System.out.println("[AdminController] 👤 Username from token: " + username);
        return adminService.changePassword(username, passwordData);
    }

    // ✅ Forgot Password - Send Reset Link
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        // return adminService.sendPasswordResetLink(request.get("username"), request.get("email"));

        System.out.println("[AdminController] 📩 Forgot Password for username: " + request.get("username"));
        return adminService.sendPasswordResetLink(request.get("username"), request.get("email"));
    }

    // ✅ Reset Password - After clicking the reset link
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        // return adminService.resetPassword(request.get("token"), request.get("newPassword"));

        System.out.println("[AdminController] 🔁 Reset Password with token: " + request.get("token"));
        return adminService.resetPassword(request.get("token"), request.get("newPassword"));
    }

    // ✅ Fetch an admin by username
    @GetMapping("/{username}")
    public ResponseEntity<?> getAdminByUsername(@PathVariable String username) {
        // Optional<Admin> adminOptional = adminService.findByUsername(username);
        // if (adminOptional.isPresent()) {
        //     return ResponseEntity.ok(adminOptional.get()); // ✅ Return 200 OK with admin data
        // } else {
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found"); // ✅ Return 404 if not found
        // }

        System.out.println("[AdminController] 🔍 Fetch Admin by username: " + username);
        Optional<Admin> adminOptional = adminService.findByUsername(username);
        if (adminOptional.isPresent()) {
            return ResponseEntity.ok(adminOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
    }
}