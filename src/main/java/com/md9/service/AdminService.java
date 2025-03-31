package com.md9.service;

import com.md9.model.Admin;
import com.md9.repository.AdminRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final Map<String, String> resetTokens = new HashMap<>();

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    // ✅ Get the next available Admin ID
    private String generateNextAdminId() {
        return adminRepository.findTopByOrderByIdDesc()
                .map(admin -> String.valueOf(Integer.parseInt(admin.getId()) + 1))
                .orElse("1"); // Start from 1 if no admins exist
    }

    // ✅ Register a new admin with incremental ID
    public ResponseEntity<?> registerAdmin(Admin admin, String requesterUsername) {
        Optional<Admin> requester = adminRepository.findByUsername(requesterUsername);
    
        if (requester.isPresent() && requester.get().isSuperAdmin()) { // ✅ Only allow super admins
            if (adminRepository.findByUsername(admin.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("Username already exists");
            }
    
            admin.setId(generateNextAdminId()); // Assign the next incremental ID
            admin.setPassword(passwordEncoder.encode(admin.getPassword())); // Hash password
            adminRepository.save(admin);
            return ResponseEntity.ok("Admin registered successfully with ID: " + admin.getId());
        } else {
            return ResponseEntity.status(403).body("Access denied: Only super admins can register new admins");
        }
    }    

    // ✅ Update Admin Profile
    public ResponseEntity<?> updateAdmin(String username, Admin adminUpdate) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
        Admin admin = adminOpt.get();
        admin.setName(adminUpdate.getName());
        admin.setEmail(adminUpdate.getEmail());
        admin.setMobile(adminUpdate.getMobile());
        adminRepository.save(admin);
        return ResponseEntity.ok("Profile updated successfully");
    }

    // ✅ Change Password
    public ResponseEntity<?> changePassword(String username, Map<String, String> passwordData) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
        Admin admin = adminOpt.get();
        if (!passwordEncoder.matches(passwordData.get("oldPassword"), admin.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect old password");
        }
        admin.setPassword(passwordEncoder.encode(passwordData.get("newPassword")));
        adminRepository.save(admin);
        return ResponseEntity.ok("Password changed successfully");
    }

    // ✅ Forgot Password - Send Reset Link
    public ResponseEntity<?> sendPasswordResetLink(String username, String email) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isEmpty() || !adminOpt.get().getEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid username or email");
        }
        String token = UUID.randomUUID().toString();
        resetTokens.put(token, username);
        sendResetEmail(email, token);
        return ResponseEntity.ok("Password reset link sent to your email");
    }

    private void sendResetEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("MD9 Password Reset");
        message.setText("Click here to reset your password: http://localhost:4200/reset-password?token=" + token);
        mailSender.send(message);
    }

    // ✅ Reset Password
    public ResponseEntity<?> resetPassword(String token, String newPassword) {
        if (!resetTokens.containsKey(token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired reset token");
        }
        String username = resetTokens.remove(token);
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
        Admin admin = adminOpt.get();
        admin.setPassword(passwordEncoder.encode(newPassword));
        adminRepository.save(admin);
        return ResponseEntity.ok("Password has been reset successfully");
    }
}