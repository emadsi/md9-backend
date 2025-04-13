package com.md9.service;

import io.jsonwebtoken.*;
// import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "mysecurelongsecretkeywhichmustbe32byteslong!"; // Ensure it's 32 bytes

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8); // Use UTF-8 encoding
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ✅ Generate JWT Token
    public String generateToken(String username, String role) {
        String token = Jwts.builder()
                .setSubject(username)
                .claim("role", role) //Include role in token
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Valid for 30 minutes
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        System.out.println("[JwtUtil] ✅ Generated token for " + username + " with role " + role);
        return token;
    }

    // ✅ Extract Username from JWT
    public String extractUsername(String token) {
        String username = extractClaims(token).getSubject();
        System.out.println("[JwtUtil] ✅ Extracted username: " + username);

        return username;
    }

    // Extract Role from JWT
    public String extractRole(String token) {
        String role = extractClaims(token).get("role", String.class);
        System.out.println("[JwtUtil] ✅ Extracted role: " + role);
        
        return role;
    }

    // ✅ Extract Claims from JWT
    private Claims extractClaims(String token) {
        // return Jwts.parserBuilder()
        //         .setSigningKey(getSigningKey())
        //         .build()
        //         .parseClaimsJws(token)
        //         .getBody();
        
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("[JwtUtil] ❌ Error extracting claims: " + e.getMessage());
            throw e;
        }
    }

    // ✅ Extract Specific Claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractClaims(token));
    }

    // ✅ Check if Token is Valid
    public boolean isTokenValid(String token, UserDetails userDetails) {
        // final String username = extractUsername(token);
        // return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

        final String username = extractUsername(token);
        boolean valid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        System.out.println("[JwtUtil] ✅ Token valid for " + username + ": " + valid);
        return valid;
    }

    // ✅ Check if Token is Expired
    private boolean isTokenExpired(String token) {
        // return extractClaim(token, Claims::getExpiration).before(new Date());

        boolean expired = extractClaim(token, Claims::getExpiration).before(new Date());
        System.out.println("[JwtUtil] ✅ Token expired: " + expired);
        return expired;
    }
}