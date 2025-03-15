package com.md9.service;

import com.md9.model.Admin;
import com.md9.repository.AdminRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
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
        if (requester.isPresent() && requester.get().isSuperAdmin()) {
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
}