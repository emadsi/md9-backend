package com.md9.config;

import com.md9.service.JwtFilter;
import com.md9.service.AdminUserDetailsService;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AdminUserDetailsService adminUserDetailsService;

    public SecurityConfig(JwtFilter jwtFilter, AdminUserDetailsService adminUserDetailsService) {
        this.jwtFilter = jwtFilter;
        this.adminUserDetailsService = adminUserDetailsService;
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http
    // .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Apply
    // CORS
    // .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
    // .sessionManagement(session ->
    // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless
    // // session
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers(
    // "/api/auth/login",
    // "/api/auth/forgot-password",
    // "/api/auth/reset-password",
    // "/api/timeslots/all",
    // "/api/disabledTimeslots/all",
    // "/api/reservations/create")
    // .permitAll() // Public endpoints
    // .requestMatchers("/api/admins/register").hasAuthority("SUPER_ADMIN") // ✅
    // Only allow super
    // // admins to register
    // .requestMatchers("/swagger-ui/**", "/v3/api-docs/**",
    // "/swagger-resources/**", "/webjars/**")
    // .permitAll() // Allow Swagger
    // .anyRequest().authenticated() // Secure all other endpoints
    // )
    // .authenticationProvider(authenticationProvider()) // Set Authentication
    // Provider
    // .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //
    // Add JWT filter

    // return http.build();
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 🔐 Secure only specific authenticated routes
                        .requestMatchers(
                                "/api/admins/update",
                                "/api/admins/change-password",
                                "/api/admins/{username}",
                                "/api/cancellations/report",
                                "/api/reservations/search",
                                "/api/reservations/all",
                                "/api/timeslots/add",
                                "/api/timeslots/block",
                                "/api/timeslots/delete",
                                "/api/timeslots/block-all")
                        .authenticated()

                        // 🔐 Route restricted to SUPER_ADMIN
                        .requestMatchers("/api/admins/register").hasAuthority("SUPER_ADMIN")

                        // 🔓 Everything else is public
                        .anyRequest().permitAll())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // ✅ Allow Angular frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(adminUserDetailsService); // ✅ Ensure correct UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}