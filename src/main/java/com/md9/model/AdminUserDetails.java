package com.md9.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AdminUserDetails implements UserDetails {

    private final Admin admin;

    public AdminUserDetails(Admin admin) {
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // return admin.getIsSuperAdmin()
        //     ? List.of(new SimpleGrantedAuthority("SUPER_ADMIN")) // ✅ match SecurityConfig
        //     : List.of(new SimpleGrantedAuthority("ADMIN"));

        String role = admin.getIsSuperAdmin() ? "SUPER_ADMIN" : "ADMIN";
        System.out.println("[AdminUserDetails] 👤 Role assigned for " + admin.getUsername() + ": " + role);
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}