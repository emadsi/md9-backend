package com.md9.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AdminUserDetailsService adminUserDetailsService; // ✅ Use the correct service

    public JwtFilter(JwtUtil jwtUtil, AdminUserDetailsService adminUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.adminUserDetailsService = adminUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

    String path = request.getRequestURI();

        // ✅ Skip JWT validation for public endpoints
    if (path.startsWith("/api/auth/")
        || path.equals("/api/timeslots/all")
        || path.equals("/api/disabledTimeslots/all")
        || path.startsWith("/swagger-ui/")
        || path.startsWith("/v3/api-docs/")
        || path.startsWith("/swagger-resources/")
        || path.startsWith("/webjars/")) {

        System.out.println("[JwtFilter] 🔓 Public endpoint accessed: " + path);
        filterChain.doFilter(request, response);
        return;
    }

        final String authHeader = request.getHeader("Authorization");
        final String token;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("[JwtFilter] ❌ Missing or malformed Authorization header"); //Logging error in Token
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);
        System.out.println("[JwtFilter] ✅ Token received: " + token); //Logging Token recieved

        try {
            String username = jwtUtil.extractUsername(token);
            System.out.println("[JwtFilter] ✅ Extracted username: " + username); //Logging username from Token

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = adminUserDetailsService.loadUserByUsername(username);
                System.out.println("[JwtFilter] ✅ Loaded user details: " + userDetails.getUsername()); //Logging userDetails

                if (jwtUtil.isTokenValid(token, userDetails)) {
                    System.out.println("[JwtFilter] ✅ Token is valid for user: " + username); // Logging Token Validation for user
                    System.out.println("[JwtFilter] ✅ Authorities: " + userDetails.getAuthorities()); // Logging Authorities

                    UsernamePasswordAuthenticationToken authToken = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("[JwtFilter] ✅ Authentication set in security context"); // Logging Authentication setting
                } else {
                    System.out.println("[JwtFilter] ❌ Token is invalid"); // Logging the Invalidation of Token
                }
            }
        } catch (ExpiredJwtException e) {
            System.out.println("[JwtFilter] ❌ JWT expired: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("[JwtFilter] ❌ Invalid JWT: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[JwtFilter] ❌ Error processing JWT: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}