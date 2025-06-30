package com.md9.service;

import com.md9.model.Admin;
import com.md9.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JwtUtil jwtUtil;

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
        System.out.println("[AdminService] 🔍 Looking up admin: " + username);
        return adminRepository.findByUsername(username);
    }

    // ✅ Get the next available Admin ID
    private String generateNextAdminId() {
        String nextId = adminRepository.findTopByOrderByAdminIdDesc()
                .map(admin -> {
                    try {
                        String lastId = admin.getAdminId();
                        if (lastId == null || lastId.trim().isEmpty()) {
                            return "1";
                        }
                        return String.valueOf(Integer.parseInt(lastId) + 1);
                    } catch (NumberFormatException e) {
                        System.out.println("[AdminService] ⚠️ Invalid or missing adminId: " + admin.getAdminId());
                        return "1";
                    }
                })
                .orElse("1");

        System.out.println("[AdminService] 🆔 Generated new Admin ID: " + nextId);
        return nextId;
    }

    // ✅ Register a new admin with incremental ID
    public ResponseEntity<?> registerAdmin(Admin admin, String requesterUsername) {
        System.out.println("[AdminService] 🛂 Register admin requested by: " + requesterUsername);

        Optional<Admin> requester = adminRepository.findByUsername(requesterUsername);

        if (requester.isPresent()) {
            Admin requesterAdmin = requester.get();
            System.out.println("[AdminService] ✅ Requester found: " + requesterAdmin.getUsername() + " | isSuperAdmin: "
                    + requesterAdmin.getIsSuperAdmin());

            if (requesterAdmin.getIsSuperAdmin()) {
                boolean usernameTaken = adminRepository.findByUsername(admin.getUsername()).isPresent();
                if (usernameTaken) {
                    System.out.println("[AdminService] ⚠️ Username already exists: " + admin.getUsername());
                    return ResponseEntity.badRequest().body("Username already exists");
                }

                admin.setId(null);

                admin.setAdminId(generateNextAdminId());
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
                adminRepository.save(admin);

                System.out.println("[AdminService] ✅ Admin registered successfully: " + admin.getUsername()
                        + " with ID: " + admin.getAdminId());
                return ResponseEntity.ok("Admin registered successfully with ID: " + admin.getAdminId());
            } else {
                System.out.println("[AdminService] ❌ Access denied: Requester is not a super admin");
            }
        } else {
            System.out.println("[AdminService] ❌ Requester not found in database");
        }

        return ResponseEntity.status(403).body("Access denied: Only super admins can register new admins");
    }

    // ✅ Update Admin Profile
    public ResponseEntity<?> updateAdmin(String username, Admin adminUpdate) {
        System.out.println("[AdminService] ✏️ Updating admin: " + username);
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isEmpty()) {
            System.out.println("[AdminService] ❌ Admin not found: " + username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }

        Admin admin = adminOpt.get();
        admin.setName(adminUpdate.getName());
        admin.setEmail(adminUpdate.getEmail());
        admin.setMobile(adminUpdate.getMobile());
        adminRepository.save(admin);

        System.out.println("[AdminService] ✅ Admin updated: " + username);
        return ResponseEntity.ok("Profile updated successfully");
    }

    // ✅ Change Password
    public ResponseEntity<?> changePassword(String username, Map<String, String> passwordData) {
        System.out.println("[AdminService] 🔑 Password change requested for: " + username);
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isEmpty()) {
            System.out.println("[AdminService] ❌ Admin not found: " + username);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
        Admin admin = adminOpt.get();
        if (!passwordEncoder.matches(passwordData.get("oldPassword"), admin.getPassword())) {
            System.out.println("[AdminService] ❌ Incorrect old password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect old password");
        }

        admin.setPassword(passwordEncoder.encode(passwordData.get("newPassword")));
        adminRepository.save(admin);

        System.out.println("[AdminService] ✅ Password changed for: " + username);
        return ResponseEntity.ok("Password changed successfully");
    }

    // ✅ Forgot Password - Send Reset Link
    public ResponseEntity<?> sendPasswordResetLink(String username, String email) {
        System.out.println("[AdminService] 📩 Reset link requested for: " + username);
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);

        if (adminOpt.isEmpty() || !adminOpt.get().getEmail().equals(email)) {
            System.out.println("[AdminService] ❌ Invalid username/email for reset: " + username + ", " + email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid username or email");
        }

        String token = UUID.randomUUID().toString();
        resetTokens.put(token, username);
        sendResetEmail(email, token);

        System.out.println("[AdminService] ✅ Reset link sent to: " + email);
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
        System.out.println("[AdminService] 🔁 Resetting password with token: " + token);

        if (!resetTokens.containsKey(token)) {
            System.out.println("[AdminService] ❌ Invalid or expired token");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired reset token");
        }

        String username = resetTokens.remove(token);
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);

        if (adminOpt.isEmpty()) {
            System.out.println("[AdminService] ❌ Admin not found for reset token");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }

        Admin admin = adminOpt.get();
        admin.setPassword(passwordEncoder.encode(newPassword));
        adminRepository.save(admin);

        System.out.println("[AdminService] ✅ Password reset for: " + username);
        return ResponseEntity.ok("Password has been reset successfully");
    }

    public boolean isAdminToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }

        String jwt = token.substring(7);
        String username = jwtUtil.extractUsername(jwt);
        Optional<Admin> admin = adminRepository.findByUsername(username);
        return admin != null;
    }
}