// // /controller/AdminController.java
// package com.md9.controller;

// import com.md9.model.Admin;
// import com.md9.service.AdminService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/admin")
// public class AdminController {

//     @Autowired
//     private AdminService adminService;

//     @PostMapping("/login")
//     public Admin login(@RequestParam String username, @RequestParam String password) {
//         Admin admin = adminService.findByUsername(username);
//         if (admin != null && admin.getPassword().equals(password)) {
//             return admin;
//         } else {
//             throw new RuntimeException("Invalid credentials");
//         }
//     }
// }
// /controller/AdminController.java
package com.md9.controller;

import com.md9.model.Admin;
import com.md9.service.JwtUtil;
import com.md9.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody Admin loginRequest) throws AuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }
        final String jwt = jwtUtil.generateToken(loginRequest.getUsername());
        return jwt;
    }
}