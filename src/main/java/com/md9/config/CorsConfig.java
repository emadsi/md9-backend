// package com.md9.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class CorsConfig {

//     // @Bean
//     // public CorsFilter corsFilter() {
//     //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     //     CorsConfiguration config = new CorsConfiguration();
//     //     config.setAllowCredentials(true);
//     //     config.addAllowedOriginPattern("http://localhost:4200"); // Allow Angular frontend
//     //     config.addAllowedHeader("*");
//     //     config.addAllowedMethod("*");
//     //     source.registerCorsConfiguration("/**", config);
//     //     return new CorsFilter(source);
//     // }

//     // @Bean
//     // public static WebMvcConfigurer corsConfigurer() {
//     //     return new WebMvcConfigurer() {
//     //         @Override
//     //         public void addCorsMappings(CorsRegistry registry) {
//     //             registry.addMapping("/**")
//     //                     .allowedOrigins("http://localhost:4200")
//     //                     .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//     //                     .allowedHeaders("*")
//     //                     .allowCredentials(true);
//     //         }
//     //     };
//     // }
// }

