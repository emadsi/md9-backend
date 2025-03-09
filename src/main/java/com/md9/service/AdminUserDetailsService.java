package com.md9.service;

import com.md9.model.Admin;
import com.md9.model.AdminUserDetails;
import com.md9.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Admin> adminOptional = adminRepository.findByUsername(username);

        if (adminOptional.isEmpty()) {
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }

        return new AdminUserDetails(adminOptional.get());
    }
}