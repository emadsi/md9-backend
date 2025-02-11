package com.md9.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private AuthenticationManager authenticationManager;

    @Autowired
    public AdminController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String authenticateAdmin(@RequestParam String username, @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        return authentication.isAuthenticated() ? "Authentication Successful" : "Authentication Failed";
    }
    

    // @Autowired
    // private MyUserDetailsService userDetailsService;

    // @Autowired
    // private JwtUtil jwtUtil;

    // @PostMapping("/login")
    // public String login(@RequestBody Admin loginRequest) throws AuthenticationException {
    //     try {
    //         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    //     } catch (Exception e) {
    //         throw new RuntimeException("Invalid credentials");
    //     }
    //     final String jwt = jwtUtil.generateToken(loginRequest.getUsername());
    //     return jwt;
    // }
}