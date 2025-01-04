// package com.md9.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.md9.service.JwtFilter;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig {

//     // @Bean
//     // public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
//     //     http.csrf((csrf) -> csrf.disable())
//     //         .authorizeHttpRequests(auth -> auth.requestMatchers(
//     //             "/swagger-ui/**", 
//     //             "/v3/api-docs/**", 
//     //             "/swagger-ui.html"
//     //         ).permitAll() // Allow Swagger access
//     //         .anyRequest().authenticated())
//     //         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//     //     return http.build();
//     // }
// }
