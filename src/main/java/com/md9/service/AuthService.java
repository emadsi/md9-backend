package com.md9.service;


import com.md9.model.Admin;
import com.md9.repository.AdminRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Register a New Admin
    public ResponseEntity<?> register(Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Admin savedAdmin = adminRepository.save(admin);
        return ResponseEntity.ok(savedAdmin);
    }

}