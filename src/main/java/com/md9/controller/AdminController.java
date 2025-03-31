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
        String requesterUsername = authentication.getName();
        return adminService.registerAdmin(admin, requesterUsername);
    }

    // ✅ Edit Admin Profile
    @PutMapping("/update")
    public ResponseEntity<?> updateAdmin(@RequestHeader("Authorization") String token, @RequestBody Admin adminUpdate) {
        String username = jwtUtil.extractUsername(token.substring(7));
        return adminService.updateAdmin(username, adminUpdate);
    }

    // ✅ Change Password (Admin)
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String token, @RequestBody Map<String, String> passwordData) {
        String username = jwtUtil.extractUsername(token.substring(7));
        return adminService.changePassword(username, passwordData);
    }

    // ✅ Forgot Password - Send Reset Link
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        return adminService.sendPasswordResetLink(request.get("username"), request.get("email"));
    }

    // ✅ Reset Password - After clicking the reset link
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        return adminService.resetPassword(request.get("token"), request.get("newPassword"));
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