package com.md9.service;

import com.md9.model.Admin;
import com.md9.repository.AdminRepository;
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

    // ✅ Find Admin by Username
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    // ✅ Register a New Admin
    public Admin registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword())); // Hash password
        return adminRepository.save(admin);
    }
}