package com.example.campapp.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Applies CORS to all paths
                .allowedOrigins("http://localhost:4200")  // Allowed origins
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowed methods
                .allowedHeaders("*")  // Allowed headers
                .allowCredentials(true)  // Credential support
                .maxAge(3600);  // Max age before a preflight request needs to be done again
    }
}
