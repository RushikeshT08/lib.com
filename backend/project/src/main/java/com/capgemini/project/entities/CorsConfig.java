package com.capgemini.project.entities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configuration for book borrows
                registry.addMapping("/api/bookborrows/**")
                    .allowedOrigins("http://127.0.0.1:5501")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowCredentials(true)
                    .maxAge(3600);
                
                // General configuration for other endpoints
                registry.addMapping("/**")
                    .allowedOrigins("http://127.0.0.1:5501")
                    .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}